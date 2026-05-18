package com.project.easywork.client.controller;

import com.project.easywork.client.domain.dto.prevention.PreventionBundleCreateD;
import com.project.easywork.client.domain.dto.prevention.PreventionBundleUpdateD;
import com.project.easywork.client.domain.dto.prevention.PreventionD;
import com.project.easywork.client.domain.dto.prevention.PreventionDetailD;
import com.project.easywork.client.service.IFacilityService;
import com.project.easywork.client.service.IPreventionService;
import com.project.easywork.client.service.ITargetService;
import com.project.easywork.common.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Preventions", description = "방지시설 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/preventions")
@RequiredArgsConstructor
public class PreventionController {
  
  private final IPreventionService preventionService;
  private final IFacilityService facilityService;
  private final ITargetService targetService;
  
  @Operation(summary = "방지시설 등록 API", description = "새로운 방지시설 정보를 데이터베이스에 저장합니다.")
  @PostMapping()
  public ResponseEntity<ApiResponse<PreventionDetailD>> register
      (
          @Valid @RequestBody PreventionBundleCreateD request
      ) {
    return ResponseEntity.ok().body(
        ApiResponse.success(preventionService.registerPreventionBundle(request))
    );
  }
  
  @Operation(summary = "방지시설 목록 조회 API", description = "전체 방지시설 목록을 조회합니다.")
  @GetMapping()
  public ResponseEntity<ApiResponse<List<PreventionD>>> getList() {
    return ResponseEntity.ok().body(ApiResponse.success(preventionService.getPreventions()));
  }
  
  @Operation(summary = "방지시설 조회 API", description = "해당 방지시설의 상세정보를 조회합니다.")
  @GetMapping("/{preventionId}")
  public ResponseEntity<ApiResponse<PreventionDetailD>> get(@PathVariable Long preventionId) {
    return ResponseEntity.ok().body(ApiResponse.success(preventionService.getPrevention(preventionId)));
  }
  
  @Operation(summary = "방지시설 수정 API", description = "해당 방지시설의 상세정보를 수정합니다.")
  @PatchMapping("/{preventionId}")
  public ResponseEntity<ApiResponse<PreventionDetailD>> update
      (
          @PathVariable Long preventionId,
          @Valid @RequestBody PreventionBundleUpdateD request
      ) {
    return ResponseEntity.ok().body(ApiResponse.success(preventionService.updatePrevention(preventionId, request)));
  }
  
  @Operation(summary = "방지시설 삭제 API", description = "방지시설 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/{preventionId}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long preventionId) {
    preventionService.removePrevention(preventionId);
    return ResponseEntity.ok(ApiResponse.success());
  }
  
  @Operation(summary = "배출시설 삭제 API", description = "배출시설 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/{preventionId}/facilities/{facilityId}")
  public ResponseEntity<ApiResponse<Void>> deleteFacility(
      @PathVariable Long preventionId,
      @PathVariable Long facilityId
  ) {
    facilityService.removeFacility(preventionId, facilityId);
    return ResponseEntity.ok(ApiResponse.success());
  }
  
  @Operation(summary = "제거대상물질 삭제 API", description = "제거대상물질 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/{preventionId}/targets/{targetId}")
  public ResponseEntity<ApiResponse<Void>> deleteTarget(
      @PathVariable Long preventionId,
      @PathVariable Long targetId
  ) {
    targetService.removeTarget(preventionId, targetId);
    return ResponseEntity.ok(ApiResponse.success());
  }
}