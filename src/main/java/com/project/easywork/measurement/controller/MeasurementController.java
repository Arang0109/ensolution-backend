package com.project.easywork.measurement.controller;

import com.project.easywork.common.api.ApiResponse;
import com.project.easywork.measurement.dto.MeasurementDraftUpdateCommandDto;
import com.project.easywork.measurement.dto.command.MeasurementCommandDto;
import com.project.easywork.measurement.service.IMeasurementService;
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
  
  @Operation(summary = "측정 데이터 업데이트 API", description = "측정 데이터를 업데이트합니다.")
  @PutMapping("/{scheduleId}/draft")
  public ResponseEntity<ApiResponse<Void>> updateDraft(
      @PathVariable Long scheduleId,
      @RequestBody MeasurementDraftUpdateCommandDto request
  ) {
    measurementService.updateDraft(scheduleId, request);
    return ResponseEntity.ok().body(ApiResponse.success());
  }
  
  @Operation(summary = "측정 데이터 저장 API", description = "측정 데이터를 저장합니다.")
  @PostMapping("/{scheduleId}/completed")
  public ResponseEntity<ApiResponse<Void>> saveDocument(
      @PathVariable Long scheduleId,
      @RequestBody MeasurementCommandDto request
  ) {
    measurementService.saveDocument(scheduleId, request);
    return ResponseEntity.ok().body(ApiResponse.success());
  }
}
