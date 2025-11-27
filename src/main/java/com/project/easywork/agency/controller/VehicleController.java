package com.project.easywork.agency.controller;

import com.project.easywork.agency.dto.VehicleDto;
import com.project.easywork.agency.service.VehicleService;
import com.project.easywork.common.util.ApiResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Workplace", description = "측정대상 사업장 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {
  
  private final VehicleService vehicleService;
  
  @Operation(summary = "차량 목록 조회 API", description = "전체 차량 목록을 조회합니다.")
  @GetMapping()
  public ResponseEntity<ApiResponseMessage<List<VehicleDto>>> getVehicles() {
    return ResponseEntity.ok(
        new ApiResponseMessage<>(true, "조회 성공", vehicleService.getList())
    );
  }
  
  @Operation(summary = "차량 조회 API", description = "해당 측정팀의 차량정보를 조회합니다.")
  @GetMapping("/{teamId}")
  public ResponseEntity<ApiResponseMessage<List<VehicleDto>>> getVehiclesByTeam(
      @PathVariable Long teamId
  ) {
    return ResponseEntity.ok(
        new ApiResponseMessage<>(true, "조회 성공", vehicleService.getListByTeam(teamId))
    );
  }
}
