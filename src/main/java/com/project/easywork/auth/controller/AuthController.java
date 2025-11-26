package com.project.easywork.auth.controller;

import com.project.easywork.auth.domain.dto.LoginRequestDto;
import com.project.easywork.auth.domain.dto.LoginResponseDto;
import com.project.easywork.common.util.ApiResponseMessage;
import com.project.easywork.auth.security.JwtTokenProvider;
import com.project.easywork.auth.security.CustomUserDetails;
import com.project.easywork.auth.service.RefreshTokenService;
import com.project.easywork.common.validator.ValidationUtils;
import com.project.easywork.user.domain.dto.UserCreateDto;
import com.project.easywork.user.domain.dto.UserResponseDto;
import com.project.easywork.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  
  private final RefreshTokenService refreshTokenService;
  private final UserService userService;
  
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;
  
  @Operation(summary = "회원 등록", description = "새로운 회원 정보를 데이터베이스에 저장")
  @PostMapping("/register")
  public ResponseEntity<ApiResponseMessage<UserResponseDto>> register
      (
          @Valid @RequestBody UserCreateDto request,
          BindingResult bindingResult
      ) {
    
    if (bindingResult.hasErrors()) {
      return ValidationUtils.handleBindingErrors(bindingResult);
    }
    
    userService.register(request);
    
    ApiResponseMessage<UserResponseDto> registerSuccess = new ApiResponseMessage<>(true, "회원등록 성공", null);
    
    return ResponseEntity.status(HttpStatus.CREATED).body(registerSuccess);
  }
  
  @Operation(summary = "로그인 API")
  @PostMapping("/login")
  public ResponseEntity<ApiResponseMessage<LoginResponseDto>> login
      (
          @RequestBody LoginRequestDto request,
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
    
    LoginResponseDto responseDto = new LoginResponseDto(
        accessToken,
        userDetails.getUsername());
    
    return ResponseEntity.ok(
        new ApiResponseMessage<>(true, "로그인에 성공했습니다.", responseDto)
    );
  }
  
  @Operation(summary = "로그아웃 API")
  @PostMapping("/logout")
  public ResponseEntity<ApiResponseMessage<Void>> logout(
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
    
    return ResponseEntity.ok(
        new ApiResponseMessage<>(true, "로그아웃 되었습니다.", null)
    );
  }
  
  @PostMapping("/refresh")
    public ResponseEntity<?> refresh(
      @CookieValue(value = "refreshToken", required = false) String refreshToken
  ) {
      // 1️⃣ 유효성 검증 (서명, 만료시간)
      if (!jwtTokenProvider.validateToken(refreshToken)) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(new ApiResponseMessage<>(false, "Refresh Token이 유효하지 않습니다.", null));
      }
      
      // 2️⃣ username 추출
      String username = jwtTokenProvider.getUsername(refreshToken);
      
      // 3️⃣ 저장된 refreshToken과 일치 여부 확인
      String storedToken = refreshTokenService.getRefreshToken(username);
      if (storedToken == null || !storedToken.equals(refreshToken)) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(new ApiResponseMessage<>(false, "Refresh Token이 일치하지 않습니다.", null));
      }
      
      // 4️⃣ Access Token 재발급
      String newAccessToken = jwtTokenProvider.generateAccessToken(username);
      
      return ResponseEntity.ok(
          new ApiResponseMessage<>(true, "Access Token 재발급 성공", newAccessToken)
      );
  }
}

