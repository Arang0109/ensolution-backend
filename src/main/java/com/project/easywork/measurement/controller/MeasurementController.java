package com.project.easywork.measurement.controller;

import com.project.easywork.common.api.ApiResponse;
import com.project.easywork.measurement.dto.SaveDraftCommandD;
import com.project.easywork.measurement.service.IMeasurementService;
import com.project.easywork.plan.service.impl.PlanApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Measurement", description = "측정 데이터 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/measurement")
@RequiredArgsConstructor
public class MeasurementController {
  
  private final IMeasurementService measurementService;
  private final PlanApplicationService planApplicationService;
  
  @Operation(summary = "데이터 저장 API", description = "데이터를 저장합니다.")
  @PostMapping("/{planId}/completed")
  public ResponseEntity<ApiResponse<Void>> submitDocument(
      @PathVariable Long planId
  ) {
    measurementService.submitDocument(planId);
    return ResponseEntity.ok().body(ApiResponse.success());
  }
  
  @Operation(summary = "데이터 임시저장 API", description = "데이터를 임시저장합니다.")
  @PatchMapping("/{planId}/draft")
  public ResponseEntity<ApiResponse<Void>> saveDocument(
      @PathVariable Long planId,
      @RequestBody SaveDraftCommandD dto
  ) {
    planApplicationService.saveDraft(planId, dto);
    return ResponseEntity.ok().body(ApiResponse.success());
  }
}