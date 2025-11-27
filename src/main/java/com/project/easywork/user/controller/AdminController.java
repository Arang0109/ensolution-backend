package com.project.easywork.user.controller;

import com.project.easywork.common.util.ApiResponseMessage;
import com.project.easywork.user.domain.dto.UserResponseDto;
import com.project.easywork.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Admin", description = "관리자 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
  
  private final UserService userService;
  
  @Operation(summary = "전체 회원조회 API", description = "전체 회원 목록을 조회합니다.")
  @GetMapping("/users")
  public ResponseEntity<ApiResponseMessage<List<UserResponseDto>>> getUserList() {
    return ResponseEntity.ok(
        new ApiResponseMessage<>(true, "조회 성공", userService.findAll())
    );
  }
}
