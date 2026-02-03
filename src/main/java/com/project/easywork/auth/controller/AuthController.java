package com.project.easywork.auth.controller;

import com.project.easywork.auth.domain.dto.LoginRequestD;
import com.project.easywork.auth.domain.dto.LoginResponseD;
import com.project.easywork.common.api.ApiResponse;
import com.project.easywork.auth.security.JwtTokenProvider;
import com.project.easywork.auth.security.CustomUserDetails;
import com.project.easywork.auth.service.RefreshTokenService;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Tag(name = "Auth", description = "인증/인가 관련 API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  
  private final RefreshTokenService refreshTokenService;
  private final IUserService IUserService;
  
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;
  
  @Operation(summary = "회원가입 API", description = "새로운 회원 정보를 데이터베이스에 저장합니다.")
  @PostMapping("/register")
  public ResponseEntity<ApiResponse<UserD>> register
      (
          @Valid @RequestBody UserCreateD request
      ) {
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
    
    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
    String accessToken = jwtTokenProvider.generateAccessToken(userDetails.getUsername());
    String refreshToken = jwtTokenProvider.generateRefreshToken(userDetails.getUsername());
    
    refreshTokenService.saveRefreshToken(
        userDetails.getUsername(),
        refreshToken,
        7, TimeUnit.DAYS
    );
    
    ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken)
        .httpOnly(true)
        .secure(true)
        .path("/")
        .sameSite("None")
        .maxAge(60 * 60 * 24 * 7)
        .build();
    
    httpResponse.setHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)  // "ROLE_ADMIN"
        .map(auth -> auth.replace("ROLE_", "")) // "ADMIN" (Optional)
        .toList();
    
    LoginResponseD responseDto = new LoginResponseD(
        accessToken,
        userDetails.getUsername(),
        roles);
    
    return ResponseEntity.ok().body(ApiResponse.success(responseDto));
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
      // 1️⃣ 유효성 검증 (서명, 만료시간)
      if (!jwtTokenProvider.validateToken(refreshToken)) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ApiResponse.error("Refresh Token이 유효하지 않습니다."));
      }
      
      // 2️⃣ username 추출
      String username = jwtTokenProvider.getUsername(refreshToken);
      
      // 3️⃣ 저장된 refreshToken과 일치 여부 확인
      String storedToken = refreshTokenService.getRefreshToken(username);
      if (storedToken == null || !storedToken.equals(refreshToken)) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ApiResponse.error("Refresh Token이 일치하지 않습니다."));
      }
      
      // 4️⃣ Access Token 재발급
      String newAccessToken = jwtTokenProvider.generateAccessToken(username);
      
      return ResponseEntity.ok().body(ApiResponse.success(newAccessToken));
  }
}

