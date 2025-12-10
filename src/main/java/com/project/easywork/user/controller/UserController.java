package com.project.easywork.user.controller;

import com.project.easywork.common.api.ApiResponse;
import com.project.easywork.user.domain.dto.PasswordUpdateDto;
import com.project.easywork.user.domain.dto.UserResponseDto;
import com.project.easywork.user.domain.dto.UserUpdateDto;
import com.project.easywork.auth.security.CustomUserDetails;
import com.project.easywork.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "사용자 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
  
  private final UserService userService;
  
  @Operation(summary = "프로필 조회 API", description = "개인 프로필을 조회합니다.")
  @GetMapping("/me")
  public ResponseEntity<ApiResponse<UserResponseDto>> get(@AuthenticationPrincipal CustomUserDetails userDetails) {
    String username = userDetails.getUsername();
    return ResponseEntity.ok().body(ApiResponse.success(userService.getProfileByUsername(username)));
  }
  
  @Operation(summary = "프로필 수정 API", description = "개인 프로필을 수정합니다.")
  @PatchMapping("/me")
  public ResponseEntity<ApiResponse<UserResponseDto>> update
      (
          @AuthenticationPrincipal CustomUserDetails userDetails,
          @Valid @RequestBody UserUpdateDto request
      ) {
    
    request.setUserId(userDetails.getUser().getId());
    
    return ResponseEntity.ok().body(ApiResponse.success(userService.update(request)));
  }
  
  @Operation(summary = "비밀번호 변경 API", description = "비밀번호를 수정합니다.")
  @PatchMapping("/me/password")
  public ResponseEntity<ApiResponse<UserResponseDto>> updatePassword
      (
          @AuthenticationPrincipal CustomUserDetails userDetails,
          @Valid @RequestBody PasswordUpdateDto request
      ) {
    return ResponseEntity.ok().body(
        ApiResponse.success(userService.updatePassword(userDetails.getUser().getId(), request)));
  }
  
  @Operation(summary = "회원탈퇴 API", description = "회원 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/me")
  public ResponseEntity<ApiResponse<Void>> delete(
      @AuthenticationPrincipal CustomUserDetails userDetails
  ) {
    userService.removeUser(userDetails);
    return ResponseEntity.ok().body(ApiResponse.success());
  }
}
