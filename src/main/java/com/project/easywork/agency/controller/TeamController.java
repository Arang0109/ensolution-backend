package com.project.easywork.agency.controller;

import com.project.easywork.agency.domain.dto.*;
import com.project.easywork.agency.service.ITeamService;
import com.project.easywork.common.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Team", description = "측정대행업체 측정팀 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {
  
  private final ITeamService teamService;
  
  @Operation(summary = "측정팀 등록 API", description = "새로운 측정팀 정보를 데이터베이스에 저장합니다.")
  @PostMapping()
  public ResponseEntity<ApiResponse<TeamResponseDto>> register(
      @Valid @RequestBody TeamCreateRequestDto request
  ) {
    return ResponseEntity.ok().body(ApiResponse.success(teamService.register(request)));
  }
  
  @Operation(summary = "측정팀 목록 조회 API", description = "전체 측정팀 목록을 조회합니다.")
  @GetMapping()
  public ResponseEntity<ApiResponse<List<TeamResponseDto>>> getList() {
    return ResponseEntity.ok().body(ApiResponse.success(teamService.getList()));
  }
  
  @Operation(summary = "측정팀 조회 API", description = "해당 측정팀의 상세정보를 조회합니다.")
  @GetMapping("/{teamId}")
  public ResponseEntity<ApiResponse<TeamDetailResponseDto>> get(@PathVariable Long teamId) {
    return ResponseEntity.ok().body(ApiResponse.success(teamService.get(teamId)));
  }
  
  @Operation(summary = "측정팀 수정 API", description = "해당 측정팀의 상세정보를 수정합니다.")
  @PatchMapping("/{teamId}")
  public ResponseEntity<ApiResponse<TeamResponseDto>> update
      (
          @PathVariable Long teamId,
          @Valid @RequestBody TeamUpdateRequestDto request
      ) {
    return ResponseEntity.ok(ApiResponse.success(teamService.update(teamId, request)));
  }
  
  @Operation(summary = "측정팀 장비 등록/삭제 API")
  @PatchMapping("/{teamId}/particular-equip")
  public ResponseEntity<ApiResponse<TeamResponseDto>> updateParticularEquip
      (
          @PathVariable Long teamId,
          @RequestBody ParticularEquipUpdateRequestDto request
      ) {
    return ResponseEntity.ok(ApiResponse.success(teamService.updateParticularEquip(teamId, request.getEquipmentId())));
  }
  
  @Operation(summary = "측정팀 피토우관 등록/삭제 API")
  @PatchMapping("/{teamId}/pitotTube")
  public ResponseEntity<ApiResponse<TeamResponseDto>> updatePitotTube
      (
          @PathVariable Long teamId,
          @RequestBody PitotTubeUpdateRequestDto request
      ) {
    return ResponseEntity.ok(ApiResponse.success(teamService.updatePitotTube(teamId, request.getPitotTubeId())));
  }
  
  @Operation(summary = "측정팀 삭제 API", description = "측정팀 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/{teamId}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long teamId) {
    teamService.delete(teamId);
    return ResponseEntity.ok(ApiResponse.success());
  }
}