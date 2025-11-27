package com.project.easywork.client.controller;

import com.project.easywork.client.domain.dto.workplace.*;
import com.project.easywork.common.util.ApiResponse;
import com.project.easywork.common.validator.ValidationUtils;
import com.project.easywork.client.service.IWorkplaceService;
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
@RequestMapping("/api/workplaces")
@RequiredArgsConstructor
public class WorkplaceController {
  
  private final IWorkplaceService workplaceService;
  
  @Operation(summary = "사업장 등록 API", description = "새로운 사업장 정보를 데이터베이스에 저장합니다.")
  @PostMapping()
  public ResponseEntity<ApiResponse<Void>> registerWorkplace
      (
          @Valid @RequestBody WorkplaceCreateRequestDto request,
          BindingResult bindingResult
      ) {
    if (bindingResult.hasErrors()) {
      return ValidationUtils.handleBindingErrors(bindingResult);
    }
    workplaceService.registerWorkplace(request);
    return ResponseEntity.ok().body(ApiResponse.ok());
  }
  
  @Operation(summary = "사업장 목록 조회 API", description = "전체 사업장 목록을 조회합니다.")
  @GetMapping()
  public ResponseEntity<ApiResponse<List<WorkplaceResponseDto>>> getWorkplaces() {
    return ResponseEntity.ok().body(ApiResponse.ok(workplaceService.getWorkplaces()));
  }
  
  @Operation(summary = "사업장 조회 API", description = "해당 사업장의 상세정보를 조회합니다.")
  @GetMapping("/{workplaceId}")
  public ResponseEntity<ApiResponse<WorkplaceResponseDto>> getWorkplace(@PathVariable Long workplaceId) {
    WorkplaceResponseDto response = workplaceService.getWorkplace(workplaceId);
    return ResponseEntity.ok(new ApiResponse<>(true, "단건 조회 성공", response));
  }
  
  @Operation(summary = "사업장 수정 API", description = "해당 사업장의 상세정보를 수정합니다.")
  @PatchMapping("/{workplaceId}")
  public ResponseEntity<ApiResponse<WorkplaceResponseDto>> updateWorkplace
      (
          @PathVariable Long workplaceId,
          @Valid @RequestBody WorkplaceUpdateRequestDto request
      ) {
    return ResponseEntity.ok().body(ApiResponse.ok(workplaceService.updateWorkplace(workplaceId, request)));
  }
  
  @Operation(summary = "사업장 삭제 API", description = "사업장 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/{workplaceId}")
  public ResponseEntity<ApiResponse<Void>> removeWorkplace(@PathVariable Long workplaceId) {
    workplaceService.removeWorkplace(workplaceId);
    return ResponseEntity.ok(ApiResponse.ok());
  }
}