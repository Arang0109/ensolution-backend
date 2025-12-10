package com.project.easywork.client.controller;

import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementCreateRequestDto;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementResponseDto;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementUpdateRequestDto;
import com.project.easywork.client.service.IStackMeasurementService;
import com.project.easywork.common.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "StackMeasurement", description = "측정시설 내 측정항목 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/stack-measurements")
@RequiredArgsConstructor
public class StackMeasurementController {
  
  private final IStackMeasurementService stackMeasurementService;
  
  @Operation(summary = "측정항목 등록 API", description = "새로운 측정시설 내 측정항목 정보를 데이터베이스에 저장합니다.")
  @PostMapping()
  public ResponseEntity<ApiResponse<StackMeasurementResponseDto>> register
      (
          @Valid @RequestBody StackMeasurementCreateRequestDto request
      ) {
    return ResponseEntity.ok().body(ApiResponse.success(stackMeasurementService.registerStackMeasurement(request)));
  }
  
  @Operation(summary = "측정시설 내 측정항목 조회 API", description = "해당 측정시설 내 측정항목의 상세정보를 조회합니다.")
  @GetMapping("/{stackMeasurementId}")
  public ResponseEntity<ApiResponse<StackMeasurementResponseDto>> get(@PathVariable Long stackMeasurementId) {
    return ResponseEntity.ok().body(ApiResponse.success(stackMeasurementService.getStackMeasurement(stackMeasurementId)));
  }
  
  @Operation(summary = "특정 측정시설의 측정항목 목록 조회 API", description = "해당 측정시설에 등록된 측정항목 목록을 조회합니다.")
  @GetMapping("/stacks/{stackId}")
  public ResponseEntity<ApiResponse<List<StackMeasurementResponseDto>>> getListByStack(@PathVariable Long stackId) {
    return ResponseEntity.ok(ApiResponse.success(stackMeasurementService.getStackMeasurementsByStack(stackId)));
  }
  
  @Operation(summary = "측정항목 수정 API", description = "해당 측정시설 내 측정항목의 상세정보를 수정합니다.")
  @PatchMapping("/{stackMeasurementId}")
  public ResponseEntity<ApiResponse<StackMeasurementResponseDto>> update
      (
          @PathVariable Long stackMeasurementId,
          @Valid @RequestBody StackMeasurementUpdateRequestDto request
      ) {
    return ResponseEntity.ok().body(ApiResponse.success(stackMeasurementService.updateStackMeasurement(stackMeasurementId, request)));
  }
  
  @Operation(summary = "측정항목 삭제 API", description = "측정시설 내 측정항목 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/{stackMeasurementId}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long stackMeasurementId) {
    stackMeasurementService.removeStackMeasurement(stackMeasurementId);
    return ResponseEntity.ok(ApiResponse.success());
  }
}
