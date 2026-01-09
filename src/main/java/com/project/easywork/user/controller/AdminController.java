package com.project.easywork.user.controller;

import com.project.easywork.common.api.ApiResponse;
import com.project.easywork.user.domain.dto.UserD;
import com.project.easywork.user.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
  
  private final IUserService IUserService;
  
  @Operation(summary = "전체 회원조회 API", description = "전체 회원 목록을 조회합니다.")
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/users")
  public ResponseEntity<ApiResponse<List<UserD>>> getList() {
    return ResponseEntity.ok(ApiResponse.success(IUserService.findAll()));
  }
}