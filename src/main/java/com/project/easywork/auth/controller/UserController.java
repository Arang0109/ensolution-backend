package com.project.easywork.auth.controller;

import com.project.easywork.auth.dto.UserCreateDto;
import com.project.easywork.common.util.ApiResponseMessage;
import com.project.easywork.auth.dto.UserResponseDto;
import com.project.easywork.auth.dto.UserUpdateDto;
import com.project.easywork.auth.security.CustomUserDetails;
import com.project.easywork.auth.service.UserService;
import com.project.easywork.common.validator.ValidationUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
  
  private final UserService userService;
  
  @Operation(summary = "회원 등록", description = "새로운 회원 정보를 데이터베이스에 저장")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "생성 성공"),
      @ApiResponse(responseCode = "400", description = "요청 형식 오류")
  })
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
  
  @Operation(summary = "회원 프로필 조회")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "조회 성공"),
      @ApiResponse(responseCode = "401", description = "인증 실패"),
      @ApiResponse(responseCode = "404", description = "사용자가 존재하지 않음")
  })
  @GetMapping("/profile")
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
  @PatchMapping("/profile")
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
  
  @Operation(summary = "전체 회원 조회")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "조회 성공"),
      @ApiResponse(responseCode = "401", description = "인증 실패"),
      @ApiResponse(responseCode = "404", description = "사용자가 존재하지 않음")
  })
  @GetMapping()
  public ResponseEntity<ApiResponseMessage<List<UserResponseDto>>> getUserList() {
    return ResponseEntity.ok(
        new ApiResponseMessage<>(true, "조회 성공", userService.findAll())
    );
  }
}
