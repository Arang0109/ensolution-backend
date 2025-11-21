package com.project.easywork.agency.controller;

import com.project.easywork.agency.dto.VehicleDto;
import com.project.easywork.agency.service.VehicleService;
import com.project.easywork.common.util.ApiResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {
  
  private final VehicleService vehicleService;
  
  @Operation(summary = "차량 전체 조회")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "조회 성공"),
      @ApiResponse(responseCode = "401", description = "인증 실패"),
      @ApiResponse(responseCode = "404", description = "차량이 존재하지 않음")
  })
  @GetMapping()
  public ResponseEntity<ApiResponseMessage<List<VehicleDto>>> getVehicles() {
    return ResponseEntity.ok(
        new ApiResponseMessage<>(true, "조회 성공", vehicleService.getList())
    );
  }
  
  @Operation(summary = "팀별 차량 조회")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "조회 성공"),
      @ApiResponse(responseCode = "401", description = "인증 실패"),
      @ApiResponse(responseCode = "404", description = "차량이 존재하지 않음")
  })
  @GetMapping("/{teamId}")
  public ResponseEntity<ApiResponseMessage<List<VehicleDto>>> getVehiclesByTeam(
      @PathVariable Long teamId
  ) {
    return ResponseEntity.ok(
        new ApiResponseMessage<>(true, "조회 성공", vehicleService.getListByTeam(teamId))
    );
  }
}
