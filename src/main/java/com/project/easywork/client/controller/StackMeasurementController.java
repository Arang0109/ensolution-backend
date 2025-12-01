package com.project.easywork.client.controller;

import com.project.easywork.client.domain.dto.stack.StackDetailResponseDto;
import com.project.easywork.client.domain.dto.stack.StackResponseDto;
import com.project.easywork.client.domain.dto.stack.StackUpdateRequestDto;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementCreateRequestDto;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementResponseDto;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementUpdateRequestDto;
import com.project.easywork.client.service.IStackMeasurementService;
import com.project.easywork.common.util.ApiResponse;
import com.project.easywork.common.util.ValidationUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "StackMeasurement", description = "측정시설 내 측정항목 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/stack-measurements")
@RequiredArgsConstructor
public class StackMeasurementController {
  private final IStackMeasurementService stackMeasurementService;
  
  @Operation(summary = "측정시설 내 측정항목 등록 API", description = "새로운 측정시설 내 측정항목 정보를 데이터베이스에 저장합니다.")
  @PostMapping()
  public ResponseEntity<ApiResponse<StackMeasurementResponseDto>> registerStack
      (
          @Valid @RequestBody StackMeasurementCreateRequestDto request,
          BindingResult bindingResult
      ) {
    if (bindingResult.hasErrors()) {
      return ValidationUtils.handleBindingErrors(bindingResult);
    }
    return ResponseEntity.ok().body(ApiResponse.ok(stackMeasurementService.registerStackMeasurement(request)));
  }
  
  @Operation(summary = "측정시설 내 측정항목 조회 API", description = "해당 측정시설 내 측정항목의 상세정보를 조회합니다.")
  @GetMapping("/{stackMeasurementId}")
  public ResponseEntity<ApiResponse<StackMeasurementResponseDto>> getStackMeasurement(@PathVariable Long stackMeasurementId) {
    return ResponseEntity.ok().body(ApiResponse.ok(stackMeasurementService.getStackMeasurement(stackMeasurementId)));
  }
  
  @Operation(summary = "측정시설 내 측정항목 수정 API", description = "해당 측정시설 내 측정항목의 상세정보를 수정합니다.")
  @PatchMapping("/{stackMeasurementId}")
  public ResponseEntity<ApiResponse<StackMeasurementResponseDto>> updateStackMeasurement
      (
          @PathVariable Long stackMeasurementId,
          @Valid @RequestBody StackMeasurementUpdateRequestDto request
      ) {
    return ResponseEntity.ok().body(ApiResponse.ok(stackMeasurementService.updateStackMeasurement(stackMeasurementId, request)));
  }
  
  @Operation(summary = "측정시설 내 측정항목 삭제 API", description = "측정시설 내 측정항목 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/{stackMeasurementId}")
  public ResponseEntity<ApiResponse<Void>> removeStackMeasurement(@PathVariable Long stackMeasurementId) {
    stackMeasurementService.removeStackMeasurement(stackMeasurementId);
    return ResponseEntity.ok(ApiResponse.ok());
  }
}
