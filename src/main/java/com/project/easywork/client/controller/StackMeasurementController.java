package com.project.easywork.client.controller;

import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementCreateRequestDto;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementResponseDto;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
