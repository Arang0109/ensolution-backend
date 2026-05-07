package com.project.easywork.plan.controller;

import com.project.easywork.common.api.ApiResponse;
import com.project.easywork.measurement.domain.dto.command.StatusUpdateCommandD;
import com.project.easywork.plan.domain.dto.*;
import com.project.easywork.plan.service.IPlanService;
import com.project.easywork.plan.service.impl.PlanApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Plan", description = "현장측정계획 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {
  
  private final IPlanService planService;
  private final PlanApplicationService planApplicationService;
  
  @Operation(
      summary = "측정계획 등록 API",
      description = "새로운 측정계획 정보를 데이터베이스에 저장합니다."
  )
  @PostMapping()
  public ResponseEntity<ApiResponse<PlanD>> register(
      @Valid @RequestBody PlanCreateBundleD request
  ) {
    return ResponseEntity.ok().body(ApiResponse.success(planApplicationService.register(request)));
  }
  
  @Operation(summary = "측정계획 목록 조회 API", description = "전체 측정일정 목록을 조회합니다.")
  @GetMapping()
  public ResponseEntity<ApiResponse<List<PlanTableViewD>>> getList() {
    return ResponseEntity.ok().body(ApiResponse.success(planService.getList()));
  }
  
  @Operation(summary = "측정계획 상세 조회 API", description = "해당 측정계획의 상세정보를 조회합니다.")
  @GetMapping("/{planId}")
  public ResponseEntity<ApiResponse<PlanDetailD>> get(
      @PathVariable Long planId
  ) {
    return ResponseEntity.ok().body(ApiResponse.success(planService.getPlan(planId)));
  }
  
  @Operation(summary = "측정상태 수정 API", description = "해당 측정계획의 측정상태를 수정합니다.")
  @PatchMapping("/{planId}/status")
  public ResponseEntity<ApiResponse<PlanD>> updateStatus
      (
          @PathVariable Long planId,
          @Valid @RequestBody StatusUpdateCommandD request
      ) {
    
    return ResponseEntity.ok().body(ApiResponse.success(planApplicationService.updateStatus(planId, request)));
  }
  
  @Operation(summary = "측정계획 삭제 API", description = "측정계획 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/{planId}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long planId) {
    planApplicationService.delete(planId);
    return ResponseEntity.ok().body(ApiResponse.success("삭제 되었습니다.", null));
  }
}