package com.project.easywork.equipment.controller;

import com.project.easywork.common.api.ApiResponse;
import com.project.easywork.equipment.domain.dto.*;
import com.project.easywork.equipment.service.IEquipmentService;
import com.project.easywork.equipment.service.IPitotTubeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Equipment", description = "측정장비 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/equipments")
@RequiredArgsConstructor
public class EquipmentController {
  
  private final IEquipmentService equipmentService;
  private final IPitotTubeService pitotTubeService;
  
  @Operation(
      summary = "측정장비 등록 API",
      description = "새로운 측정장비 정보를 데이터베이스에 저장합니다."
  )
  @PostMapping()
  public ResponseEntity<ApiResponse<EquipD>> register(
      @Valid @RequestBody EquipCreateD request
  ) {
    return ResponseEntity.ok().body(ApiResponse.success(equipmentService.register(request)));
  }
  
  @Operation(summary = "측정장비 목록 조회 API", description = "전체 측정장비 목록을 조회합니다.")
  @GetMapping()
  public ResponseEntity<ApiResponse<List<EquipD>>> getList() {
    return ResponseEntity.ok().body(ApiResponse.success(equipmentService.getList()));
  }
  
  @Operation(summary = "피토우관 목록 조회 API", description = "전체 측정장비 목록을 조회합니다.")
  @GetMapping("/pitot")
  public ResponseEntity<ApiResponse<PitotTableViewD>> getListForPitot() {
    return ResponseEntity.ok().body(ApiResponse.success(pitotTubeService.getList()));
  }
  
  @Operation(summary = "측정장비 상세 조회 API", description = "해당 측정장비의 상세정보를 조회합니다.")
  @GetMapping("/{equipmentId}")
  public ResponseEntity<ApiResponse<EquipD>> get(
      @PathVariable Long equipmentId
  ) {
    return ResponseEntity.ok().body(ApiResponse.success(equipmentService.getEquipment(equipmentId)));
  }
  
  @Operation(summary = "측정장비 수정 API", description = "해당 측정장비의 상세정보를 수정합니다.")
  @PatchMapping("/{equipmentId}")
  public ResponseEntity<ApiResponse<EquipD>> update
      (
          @PathVariable Long equipmentId,
          @Valid @RequestBody EquipUpdateD request
      ) {
    
    return ResponseEntity.ok().body(ApiResponse.success(equipmentService.update(equipmentId, request)));
  }
  
  @Operation(summary = "측정장비 교정날짜 업데이트 API", description = "해당 측정장비의 교정날짜를 업데이트합니다.")
  @PatchMapping("/{equipmentId}/calibration")
  public ResponseEntity<ApiResponse<EquipD>> updateCalibrationDate(
      @PathVariable Long equipmentId,
      @Valid @RequestBody EquipCalibrationDateUpdateD request) {
    return ResponseEntity.ok().body(ApiResponse.success(equipmentService.updateCalibrationDate(equipmentId, request)));
  }
  
  @Operation(summary = "측정장비 삭제 API", description = "측정장비 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/{equipmentId}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long equipmentId) {
    equipmentService.delete(equipmentId);
    return ResponseEntity.ok().body(ApiResponse.success());
  }
}
