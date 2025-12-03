package com.project.easywork.agency.controller;

import com.project.easywork.agency.domain.dto.*;
import com.project.easywork.agency.service.IVehicleService;
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

@Tag(name = "Workplace", description = "측정대상 사업장 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {
  
  private final IVehicleService vehicleService;
  
  @Operation(summary = "차량 등록 API", description = "새로운 차량 정보를 데이터베이스에 저장합니다.")
  @PostMapping()
  public ResponseEntity<ApiResponse<VehicleResponseDto>> registerVehicle(
      @Valid @RequestBody VehicleCreateRequestDto request,
      BindingResult bindingResult
  ) {
    if (bindingResult.hasErrors()) {
      return ValidationUtils.handleBindingErrors(bindingResult);
    }
    return ResponseEntity.ok().body(ApiResponse.ok(vehicleService.register(request)));
  }
  
  @Operation(summary = "차량 목록 조회 API", description = "전체 차량 목록을 조회합니다.")
  @GetMapping()
  public ResponseEntity<ApiResponse<List<VehicleResponseDto>>> getVehicles() {
    return ResponseEntity.ok().body(ApiResponse.ok(vehicleService.getList()));
  }
  
  @Operation(summary = "차량 조회 API", description = "해당 차량의 상세정보를 조회합니다.")
  @GetMapping("/{vehicleId}")
  public ResponseEntity<ApiResponse<VehicleResponseDto>> vehicleId(
      @PathVariable Long vehicleId
  ) {
    return ResponseEntity.ok().body(ApiResponse.ok(vehicleService.get(vehicleId)));
  }
  
  @Operation(summary = "차량 수정 API", description = "해당 차량의 상세정보를 수정합니다.")
  @PatchMapping("/{vehicleId}")
  public ResponseEntity<ApiResponse<VehicleResponseDto>> updateVehicle
      (
          @PathVariable Long vehicleId,
          @Valid @RequestBody VehicleUpdateRequestDto request,
          BindingResult bindingResult
      ) {
    if (bindingResult.hasErrors()) {
      return ValidationUtils.handleBindingErrors(bindingResult);
    }
    return ResponseEntity.ok(ApiResponse.ok(vehicleService.update(vehicleId, request)));
  }
  
  @Operation(summary = "차량 삭제 API", description = "차량 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/{vehicleId}")
  public ResponseEntity<ApiResponse<Void>> removeVehicle(@PathVariable Long vehicleId) {
    vehicleService.delete(vehicleId);
    return ResponseEntity.ok(ApiResponse.ok());
  }
}
