package com.project.easywork.agency.controller;

import com.project.easywork.agency.domain.dto.TeamCreateD;
import com.project.easywork.agency.domain.dto.TeamD;
import com.project.easywork.agency.domain.dto.TeamUpdateD;
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
  public ResponseEntity<ApiResponse<TeamD>> register(
      @Valid @RequestBody TeamCreateD request
  ) {
    return ResponseEntity.ok().body(ApiResponse.success(teamService.register(request)));
  }
  
  @Operation(summary = "측정팀 목록 조회 API", description = "전체 측정팀 목록을 조회합니다.")
  @GetMapping()
  public ResponseEntity<ApiResponse<List<TeamD>>> getList() {
    return ResponseEntity.ok().body(ApiResponse.success(teamService.getList()));
  }
  
  @Operation(summary = "측정팀 조회 API", description = "해당 측정팀의 상세정보를 조회합니다.")
  @GetMapping("/{teamId}")
  public ResponseEntity<ApiResponse<TeamD>> get(@PathVariable Long teamId) {
    return ResponseEntity.ok().body(ApiResponse.success(teamService.get(teamId)));
  }
  
  @Operation(summary = "측정팀 수정 API", description = "해당 측정팀의 상세정보를 수정합니다.")
  @PatchMapping("/{teamId}")
  public ResponseEntity<ApiResponse<TeamD>> update
      (
          @PathVariable Long teamId,
          @Valid @RequestBody TeamUpdateD request
      ) {
    return ResponseEntity.ok(ApiResponse.success(teamService.update(teamId, request)));
  }
  
  @Operation(summary = "측정팀 삭제 API", description = "측정팀 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/{teamId}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long teamId) {
    teamService.delete(teamId);
    return ResponseEntity.ok(ApiResponse.success());
  }
}