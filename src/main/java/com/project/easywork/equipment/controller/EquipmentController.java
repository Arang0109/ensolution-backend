package com.project.easywork.equipment.controller;

import com.project.easywork.common.api.ApiResponse;
import com.project.easywork.equipment.domain.EquipType;
import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.equipment.domain.dto.EquipmentCreateReqD;
import com.project.easywork.equipment.domain.dto.EquipmentRegisterResD;
import com.project.easywork.equipment.domain.dto.EquipmentUpdateReqD;
import com.project.easywork.equipment.service.impl.EquipmentServiceDispatcher;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Equipment", description = "장비 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/equipments")
@RequiredArgsConstructor
public class EquipmentController {
  
  private final EquipmentServiceDispatcher equipmentServiceDispatcher;
  
  @Operation(summary = "장비 등록 API", description = "새로운 장비 정보를 데이터베이스에 저장합니다.")
  @PostMapping()
  public ResponseEntity<ApiResponse<EquipmentRegisterResD>> register(
      @Valid @RequestBody EquipmentCreateReqD dto
  ) {
    EquipmentDoc doc = equipmentServiceDispatcher.register(dto);
    
    return ResponseEntity.ok().body(ApiResponse.success(new EquipmentRegisterResD(doc.getId())));
  }
  
  @Operation(summary = "장비 수정 API")
  @PatchMapping("/{equipmentId}")
  public ResponseEntity<ApiResponse<EquipmentDoc>> update(
      @PathVariable String equipmentId,
      @Valid@RequestBody EquipmentUpdateReqD dto
  ) {
    return ResponseEntity.ok().body(ApiResponse.success(equipmentServiceDispatcher.update(dto, equipmentId)));
  }
  
  @Operation(summary = "전체 장비 목록 조회 API")
  @GetMapping()
  public ResponseEntity<ApiResponse<List<EquipmentDoc>>> getAllEquipmentList() {
    return ResponseEntity.ok().body(ApiResponse.success(equipmentServiceDispatcher.getAllEquipments()));
  }
  
  @Operation(summary = "입자상시료채취장비 목록 조회 API")
  @GetMapping("/particle-sampler")
  public ResponseEntity<ApiResponse<List<EquipmentDoc>>> getParticleSamplerList() {
    return ResponseEntity.ok().body(ApiResponse.success(equipmentServiceDispatcher.getList(EquipType.PARTICLE_SAMPLER)));
  }
  
  @Operation(summary = "피토우관 목록 조회 API")
  @GetMapping("/pitot-tube")
  public ResponseEntity<ApiResponse<List<EquipmentDoc>>> getPitotTubeList() {
    return ResponseEntity.ok().body(ApiResponse.success(equipmentServiceDispatcher.getList(EquipType.PITOT_TUBE)));
  }
  
  @Operation(summary = "노즐 목록 조회 API")
  @GetMapping("/nozzle")
  public ResponseEntity<ApiResponse<List<EquipmentDoc>>> getNozzleList() {
    return ResponseEntity.ok().body(ApiResponse.success(equipmentServiceDispatcher.getList(EquipType.NOZZLE)));
  }
}