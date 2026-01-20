package com.project.easywork.measurement.controller;

import com.project.easywork.common.api.ApiResponse;
import com.project.easywork.measurement.dto.DraftUpdateCommandD;
import com.project.easywork.measurement.dto.command.MeasurementCommandD;
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
  @PutMapping("/{planId}/draft")
  public ResponseEntity<ApiResponse<Void>> updateDraft(
      @PathVariable Long planId,
      @RequestBody DraftUpdateCommandD request
  ) {
    measurementService.updateDraft(planId, request);
    return ResponseEntity.ok().body(ApiResponse.success());
  }
  
  @Operation(summary = "측정 데이터 저장 API", description = "측정 데이터를 저장합니다.")
  @PostMapping("/{planId}/completed")
  public ResponseEntity<ApiResponse<Void>> saveDocument(
      @PathVariable Long planId
  ) {
    measurementService.saveDocument(planId);
    return ResponseEntity.ok().body(ApiResponse.success());
  }
  
  @Operation(summary = "입자상 측정장비 변경 API", description = "입자상 측정장비를 변경합니다.")
  @PostMapping("/{planId}/equipment")
  public ResponseEntity<ApiResponse<Void>> changeParticularEquipment(
      @PathVariable Long planId,
      @RequestBody Long equipmentId
  ) {
    measurementService.changeEquipment(planId, equipmentId);
    return ResponseEntity.ok().body(ApiResponse.success());
  }
  
  @Operation(summary = "피토우관 변경 API", description = "피토우관을 변경합니다.")
  @PostMapping("/{planId}/pitot-tube")
  public ResponseEntity<ApiResponse<Void>> changePitotTube(
      @PathVariable Long planId,
      @RequestBody Long pitotTubeId
  ) {
    measurementService.changePitotTube(planId, pitotTubeId);
    return ResponseEntity.ok().body(ApiResponse.success());
  }
}
