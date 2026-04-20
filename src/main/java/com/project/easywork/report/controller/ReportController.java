package com.project.easywork.report.controller;

import com.project.easywork.common.api.ApiResponse;
import com.project.easywork.report.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Report", description = "성적서 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {
  private final ReportService reportService;
  
  @Operation(summary = "성적서 생성 API", description = "데이터를 저장합니다.")
  @PostMapping("/{planId}")
  public ResponseEntity<ApiResponse<Void>> createReport(
      @PathVariable Long planId
  ) {
    reportService.createReport(planId);
    return ResponseEntity.ok().body(ApiResponse.success());
  }
}