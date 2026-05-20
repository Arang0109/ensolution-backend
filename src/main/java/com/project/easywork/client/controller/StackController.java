package com.project.easywork.client.controller;

import com.project.easywork.client.domain.dto.stack.*;
import com.project.easywork.client.service.IStackMeasurementService;
import com.project.easywork.common.web.ApiResponse;
import com.project.easywork.client.service.IStackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Stack", description = "측정시설 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/stacks")
@RequiredArgsConstructor
public class StackController {
  
  private final IStackService stackService;
  private final IStackMeasurementService stackMeasurementService;
  
  @Operation(summary = "측정시설 등록 API", description = "새로운 측정시설 정보를 데이터베이스에 저장합니다.")
  @PostMapping()
  public ResponseEntity<ApiResponse<StackD>> register
      (
          @Valid @RequestBody StackCreateD request
      ) {
    return ResponseEntity.ok().body(ApiResponse.success(stackService.registerStack(request)));
  }
  
  @Operation(summary = "측정시설 목록 조회 API", description = "전체 측정시설 목록을 조회합니다.")
  @GetMapping()
  public ResponseEntity<ApiResponse<List<StackD>>> getList() {
    return ResponseEntity.ok().body(ApiResponse.success(stackService.getStacks()));
  }
  
  @Operation(summary = "측정시설 조회 API", description = "해당 측정시설의 상세정보를 조회합니다.")
  @GetMapping("/{stackId}")
  public ResponseEntity<ApiResponse<StackDetailD>> get(@PathVariable Long stackId) {
    return ResponseEntity.ok().body(ApiResponse.success(stackService.getStack(stackId)));
  }
  
  @Operation(summary = "측정시설의 측정항목 목록 조회 API", description = "해당 측정시설에 등록된 측정항목 목록을 조회합니다.")
  @GetMapping("/{stackId}/measurements")
  public ResponseEntity<ApiResponse<List<MeasurementListD>>> getMeasurementsByStack(@PathVariable Long stackId) {
    return ResponseEntity.ok(ApiResponse.success(stackMeasurementService.getStackMeasurementsByStack(stackId)));
  }
  
  @Operation(summary = "측정시설 수정 API", description = "해당 측정시설의 상세정보를 수정합니다.")
  @PatchMapping("/{stackId}")
  public ResponseEntity<ApiResponse<StackD>> update
      (
          @PathVariable Long stackId,
          @Valid @RequestBody StackUpdateD request
      ) {
    return ResponseEntity.ok().body(ApiResponse.success(stackService.updateStack(stackId, request)));
  }
  
  @Operation(summary = "측정시설 삭제 API", description = "측정시설 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/{stackId}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long stackId) {
    stackService.removeStack(stackId);
    return ResponseEntity.ok(ApiResponse.success());
  }
  
  @Operation(summary = "측정시설 CSV/TXT 업로드", description = "파일을 업로드하여 측정시설을 일괄 등록합니다.")
  @PostMapping("/import")
  public ResponseEntity<ApiResponse<Void>> importStacks(
      @RequestParam("file") MultipartFile file
  ) {
    
    stackService.importStacks(file);
    
    return ResponseEntity.ok(ApiResponse.success());
  }
}
