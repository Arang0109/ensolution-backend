package com.project.easywork.client.controller;

import com.project.easywork.client.domain.dto.facility.FacilityCreateRequestDto;
import com.project.easywork.client.domain.dto.facility.FacilityResponseDto;
import com.project.easywork.client.domain.dto.facility.FacilityUpdateRequestDto;
import com.project.easywork.client.service.IFacilityService;
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

@Tag(name = "Facilities", description = "배출시설 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/facilities")
@RequiredArgsConstructor
public class FacilityController {
  
  private final IFacilityService facilityService;
  
  @Operation(summary = "배출시설 등록 API", description = "새로운 배출시설 정보를 데이터베이스에 저장합니다.")
  @PostMapping()
  public ResponseEntity<ApiResponse<Void>> registerFacility
      (
          @Valid @RequestBody FacilityCreateRequestDto request,
          BindingResult bindingResult
      ) {
    if (bindingResult.hasErrors()) {
      return ValidationUtils.handleBindingErrors(bindingResult);
    }
    facilityService.registerFacility(request);
    return ResponseEntity.ok().body(ApiResponse.ok());
  }
  
  @Operation(summary = "배출시설 수정 API", description = "해당 배출시설의 상세정보를 수정합니다.")
  @PatchMapping("/{facilityId}")
  public ResponseEntity<ApiResponse<FacilityResponseDto>> updateFacility
      (
          @PathVariable Long facilityId,
          @Valid @RequestBody FacilityUpdateRequestDto request
      ) {
    return ResponseEntity.ok().body(ApiResponse.ok(facilityService.updateFacility(facilityId, request)));
  }
  
  @Operation(summary = "배출시설 삭제 API", description = "배출시설 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/{facilityId}")
  public ResponseEntity<ApiResponse<Void>> removeFacility(@PathVariable Long facilityId) {
    facilityService.removeFacility(facilityId);
    return ResponseEntity.ok(ApiResponse.ok());
  }
}
