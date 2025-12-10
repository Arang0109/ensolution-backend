package com.project.easywork.client.controller;

import com.project.easywork.client.domain.dto.prevention.*;
import com.project.easywork.client.service.IPreventionService;
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
  
  @Operation(summary = "방지시설 등록 API", description = "새로운 방지시설 정보를 데이터베이스에 저장합니다.")
  @PostMapping()
  public ResponseEntity<ApiResponse<PreventionDetailResponseDto>> register
      (
          @Valid @RequestBody PreventionBundleCreateRequestDto request
      ) {
    return ResponseEntity.ok().body(
        ApiResponse.success(preventionService.registerPreventionBundle(request))
    );
  }
  
  @Operation(summary = "방지시설 목록 조회 API", description = "전체 방지시설 목록을 조회합니다.")
  @GetMapping()
  public ResponseEntity<ApiResponse<List<PreventionResponseDto>>> getList() {
    return ResponseEntity.ok().body(ApiResponse.success(preventionService.getPreventions()));
  }
  
  @Operation(summary = "방지시설 조회 API", description = "해당 방지시설의 상세정보를 조회합니다.")
  @GetMapping("/{preventionId}")
  public ResponseEntity<ApiResponse<PreventionDetailResponseDto>> get(@PathVariable Long preventionId) {
    return ResponseEntity.ok().body(ApiResponse.success(preventionService.getPrevention(preventionId)));
  }
  
  @Operation(summary = "방지시설 수정 API", description = "해당 방지시설의 상세정보를 수정합니다.")
  @PatchMapping("/{preventionId}")
  public ResponseEntity<ApiResponse<PreventionResponseDto>> update
      (
          @PathVariable Long preventionId,
          @Valid @RequestBody PreventionUpdateRequestDto request
      ) {
    return ResponseEntity.ok().body(ApiResponse.success(preventionService.updatePrevention(preventionId, request)));
  }
  
  @Operation(summary = "방지시설 삭제 API", description = "방지시설 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/{preventionId}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long preventionId) {
    preventionService.removePrevention(preventionId);
    return ResponseEntity.ok(ApiResponse.success());
  }
}