package com.project.easywork.equipment.controller;

import com.project.easywork.common.api.ApiResponse;
import com.project.easywork.equipment.domain.EquipType;
import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.equipment.domain.dto.EquipmentCreateReqD;
import com.project.easywork.equipment.domain.dto.EquipmentStatusUpdateReq;
import com.project.easywork.equipment.domain.dto.EquipmentUpdateReqD;
import com.project.easywork.equipment.service.impl.EquipmentService;
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
  
  private final EquipmentService equipmentService;
  
  @Operation(summary = "장비 등록 API")
  @PostMapping()
  public ResponseEntity<ApiResponse<EquipmentDoc>> register(
      @Valid @RequestBody EquipmentCreateReqD dto
  ) {
    return ResponseEntity.ok().body(ApiResponse.success(equipmentService.register(dto)));
  }
  
  @Operation(summary = "장비 수정 API")
  @PatchMapping("/{equipmentId}")
  public ResponseEntity<ApiResponse<EquipmentDoc>> update(
      @PathVariable String equipmentId,
      @Valid@RequestBody EquipmentUpdateReqD dto
  ) {
    return ResponseEntity.ok().body(ApiResponse.success(equipmentService.update(dto, equipmentId)));
  }
  
  @Operation(summary = "장비 목록조회 API")
  @GetMapping
  public ResponseEntity<ApiResponse<List<EquipmentDoc>>> getEquipmentList(
      @RequestParam(required = false) EquipType type
  ) {
    return ResponseEntity.ok(
        ApiResponse.success(equipmentService.getList(type))
    );
  }
  
  @Operation(summary = "장비 상태관리 API")
  @PatchMapping("/{equipmentId}/status")
  public ResponseEntity<ApiResponse<Void>> changeStatus(
      @PathVariable String equipmentId,
      @RequestBody EquipmentStatusUpdateReq req
  ) {
    equipmentService.changeStatus(equipmentId, req.status());
    
    return ResponseEntity.ok(ApiResponse.success());
  }
}