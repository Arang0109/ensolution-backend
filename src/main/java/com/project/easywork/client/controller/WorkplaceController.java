package com.project.easywork.client.controller;

import com.project.easywork.client.domain.dto.workplace.WorkplaceCreateD;
import com.project.easywork.client.domain.dto.workplace.WorkplaceD;
import com.project.easywork.client.domain.dto.workplace.WorkplaceDetailD;
import com.project.easywork.client.domain.dto.workplace.WorkplaceUpdateD;
import com.project.easywork.client.service.IStackService;
import com.project.easywork.common.web.ApiResponse;
import com.project.easywork.client.service.IWorkplaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Workplace", description = "측정대상 사업장 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/workplaces")
@RequiredArgsConstructor
public class WorkplaceController {
  
  private final IWorkplaceService workplaceService;
  private final IStackService stackService;
  
  @Operation(summary = "사업장 등록 API", description = "새로운 사업장 정보를 데이터베이스에 저장합니다.")
  @PostMapping()
  public ResponseEntity<ApiResponse<WorkplaceD>> register
      (
          @Valid @RequestBody WorkplaceCreateD request
      ) {
    return ResponseEntity.ok().body(ApiResponse.success(workplaceService.registerWorkplace(request)));
  }
  
  @Operation(summary = "사업장 목록 조회 API", description = "전체 사업장 목록을 조회합니다.")
  @GetMapping()
  public ResponseEntity<ApiResponse<List<WorkplaceD>>> getList() {
    return ResponseEntity.ok().body(ApiResponse.success(workplaceService.getWorkplaces()));
  }
  
  @Operation(summary = "사업장 조회 API", description = "해당 사업장의 상세정보를 조회합니다.")
  @GetMapping("/{workplaceId}")
  public ResponseEntity<ApiResponse<WorkplaceDetailD>> get(@PathVariable Long workplaceId) {
    return ResponseEntity.ok().body(ApiResponse.success(workplaceService.getWorkplace(workplaceId)));
  }
  
  @Operation(summary = "사업장 수정 API", description = "해당 사업장의 상세정보를 수정합니다.")
  @PatchMapping("/{workplaceId}")
  public ResponseEntity<ApiResponse<WorkplaceD>> update
      (
          @PathVariable Long workplaceId,
          @Valid @RequestBody WorkplaceUpdateD request
      ) {
    return ResponseEntity.ok().body(ApiResponse.success(workplaceService.updateWorkplace(workplaceId, request)));
  }
  
  @Operation(summary = "사업장 삭제 API", description = "사업장 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/{workplaceId}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long workplaceId) {
    workplaceService.removeWorkplace(workplaceId);
    return ResponseEntity.ok(ApiResponse.success());
  }
}