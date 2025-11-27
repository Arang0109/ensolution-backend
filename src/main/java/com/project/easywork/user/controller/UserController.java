package com.project.easywork.user.controller;

import com.project.easywork.common.util.ApiResponse;
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
  public ResponseEntity<ApiResponse<UserResponseDto>> getProfile
      (
          @AuthenticationPrincipal CustomUserDetails userDetails
      ) {
    
    String username = userDetails.getUsername();
    
    return ResponseEntity.ok(
        new ApiResponse<>(true, "조회 성공", userService.getProfileByUsername(username))
    );
  }
  
  @Operation(summary = "프로필 수정 API", description = "개인 프로필을 수정합니다.")
  @PatchMapping("/me")
  public ResponseEntity<ApiResponse<UserResponseDto>> patchProfile
      (
          @AuthenticationPrincipal CustomUserDetails userDetails,
          @Valid @RequestBody UserUpdateDto request
      ) {
    
    request.setUserId(userDetails.getUserId());
    UserResponseDto response = userService.update(request);
    
    return ResponseEntity.ok(
        new ApiResponse<>(true, "수정 성공", response)
    );
  }
  
  @Operation(summary = "회원탈퇴 API", description = "회원 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/me")
  public ResponseEntity<ApiResponse<Void>> deleteProfile(
      @AuthenticationPrincipal CustomUserDetails userDetails
  ) {
    
    Long id = userDetails.getUserId();
    // userService.delete 구현
    
    return ResponseEntity.ok(
        new ApiResponse<>(true, "success", null)
    );
  }
}
