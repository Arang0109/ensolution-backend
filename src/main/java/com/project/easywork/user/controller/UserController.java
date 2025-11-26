package com.project.easywork.user.controller;

import com.project.easywork.common.util.ApiResponseMessage;
import com.project.easywork.user.domain.dto.UserResponseDto;
import com.project.easywork.user.domain.dto.UserUpdateDto;
import com.project.easywork.auth.security.CustomUserDetails;
import com.project.easywork.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
  
  private final UserService userService;
  
  @Operation(summary = "회원 프로필 조회")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "조회 성공"),
      @ApiResponse(responseCode = "401", description = "인증 실패"),
      @ApiResponse(responseCode = "404", description = "사용자가 존재하지 않음")
  })
  @GetMapping("/me")
  public ResponseEntity<ApiResponseMessage<UserResponseDto>> getProfile
      (
          @AuthenticationPrincipal CustomUserDetails userDetails
      ) {
    
    String username = userDetails.getUsername();
    UserResponseDto response = userService.getProfileByUsername(username);
    
    return ResponseEntity.ok(
        new ApiResponseMessage<>(true, "조회 성공", response)
    );
  }
  
  @Operation(summary = "회원 프로필 수정")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "수정 성공"),
      @ApiResponse(responseCode = "401", description = "인증 실패"),
      @ApiResponse(responseCode = "404", description = "사용자가 존재하지 않음")
  })
  @PatchMapping("/me")
  public ResponseEntity<ApiResponseMessage<UserResponseDto>> patchProfile
      (
          @AuthenticationPrincipal CustomUserDetails userDetails,
          @Valid @RequestBody UserUpdateDto request
      ) {
    
    request.setUserId(userDetails.getUserId());
    UserResponseDto response = userService.update(request);
    
    return ResponseEntity.ok(
        new ApiResponseMessage<>(true, "수정 성공", response)
    );
  }
}
