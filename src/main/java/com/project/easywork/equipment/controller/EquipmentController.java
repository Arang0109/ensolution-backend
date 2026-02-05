package com.project.easywork.equipment.controller;

import com.project.easywork.common.api.ApiResponse;
import com.project.easywork.equipment.domain.EquipType;
import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.equipment.domain.dto.EquipmentCreateReqD;
import com.project.easywork.equipment.domain.dto.EquipmentRegisterResD;
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
  public ResponseEntity<ApiResponse<EquipmentRegisterResD>> register(
      @Valid @RequestBody EquipmentCreateReqD dto
  ) {
    EquipmentDoc doc = equipmentService.register(dto);
    
    return ResponseEntity.ok().body(ApiResponse.success(new EquipmentRegisterResD(doc.getId())));
  }
  
  @Operation(summary = "장비 수정 API")
  @PatchMapping("/{equipmentId}")
  public ResponseEntity<ApiResponse<EquipmentDoc>> update(
      @PathVariable String equipmentId,
      @Valid@RequestBody EquipmentUpdateReqD dto
  ) {
    return ResponseEntity.ok().body(ApiResponse.success(equipmentService.update(dto, equipmentId)));
  }
  
  @Operation(summary = "장비 목록 조회 API")
  @GetMapping
  public ResponseEntity<ApiResponse<List<EquipmentDoc>>> getEquipmentList(
      @RequestParam(required = false) EquipType type
  ) {
    return ResponseEntity.ok(
        ApiResponse.success(equipmentService.getList(type))
    );
  }
  
  @Operation(summary = "장비 삭제 API")
  @DeleteMapping("/{equipmentId}")
  public ResponseEntity<ApiResponse<Void>> removeEquipment(
      @PathVariable String equipmentId
  ) {
    equipmentService.deleteById(equipmentId);
    
    return ResponseEntity.ok().body(ApiResponse.success());
  }
}