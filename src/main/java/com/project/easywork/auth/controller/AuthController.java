package com.project.easywork.auth.controller;

import com.project.easywork.auth.domain.dto.LoginRequestD;
import com.project.easywork.auth.domain.dto.LoginResponseD;
import com.project.easywork.common.api.ApiResponse;
import com.project.easywork.security.domain.JwtProperties;
import com.project.easywork.security.domain.JwtToken;
import com.project.easywork.security.jwt.JwtTokenProvider;
import com.project.easywork.security.user.CustomUserDetails;
import com.project.easywork.security.token.RefreshTokenService;
import com.project.easywork.user.domain.dto.UserCreateD;
import com.project.easywork.user.domain.dto.UserD;
import com.project.easywork.user.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "인증/인가 관련 API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  
  private final RefreshTokenService refreshTokenService;
  private final IUserService IUserService;
  
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;
  private final JwtProperties jwtProperties;
  
  @Operation(summary = "회원가입 API", description = "새로운 회원 정보를 데이터베이스에 저장합니다.")
  @PostMapping("/register")
  public ResponseEntity<ApiResponse<UserD>> register(@Valid @RequestBody UserCreateD request) {
    return ResponseEntity.ok().body(ApiResponse.success(IUserService.register(request)));
  }
  
  @Operation(summary = "로그인 API", description = "회원 로그인을 수행합니다.")
  @PostMapping("/login")
  public ResponseEntity<ApiResponse<LoginResponseD>> login
    (
      @RequestBody LoginRequestD request,
      HttpServletResponse httpResponse
    ) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
    );
    
    JwtToken token = jwtTokenProvider.createToken(authentication);
    
    refreshTokenService.saveRefreshToken(
      token.username(),
      token.refreshToken(),
      jwtProperties.refreshTokenValidity()
    );
    
    ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", token.refreshToken())
      .httpOnly(true)
      .secure(true)
      .path("/")
      .sameSite("None")
      .maxAge(jwtProperties.refreshTokenValidity())
      .build();
    
    httpResponse.setHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
    
    return ResponseEntity.ok().body(ApiResponse.success(
      LoginResponseD.builder()
        .accessToken(token.accessToken())
        .username(token.username())
        .roles(token.roles())
        .build()
    ));
  }
  
  @Operation(summary = "로그아웃 API", description = "회원 로그아웃을 수행합니다.")
  @SecurityRequirement(name = "bearerAuth")
  @PostMapping("/logout")
  public ResponseEntity<ApiResponse<Void>> logout(
    @AuthenticationPrincipal CustomUserDetails userDetails,
    HttpServletResponse response
  ) {
    
    refreshTokenService.deleteRefreshToken(userDetails.getUsername());
    
    ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", null)
      .httpOnly(true)
      .secure(true)
      .path("/")
      .sameSite("None")
      .maxAge(0)
      .build();
    
    response.setHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
    
    return ResponseEntity.ok().body(ApiResponse.success());
  }
  
  @Operation(summary = "토큰 재발급 API", description = "새로운 AccessToken 발급")
  @SecurityRequirement(name = "bearerAuth")
  @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<String>> refresh(
      @CookieValue(value = "refreshToken", required = false) String refreshToken
  ) {
      if (refreshToken == null || !jwtTokenProvider.validateToken(refreshToken)) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ApiResponse.error("Refresh Token이 유효하지 않습니다."));
      }
      
      Authentication authentication = jwtTokenProvider.getAuthentication(refreshToken);
      
      String storedToken = refreshTokenService.getRefreshToken(authentication);
      
      if (storedToken == null || !storedToken.equals(refreshToken)) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ApiResponse.error("Refresh Token이 일치하지 않습니다."));
      }
      
      String accessToken = jwtTokenProvider.createAccessToken(authentication);
      
      return ResponseEntity.ok().body(ApiResponse.success(accessToken));
  }
}