package com.project.easywork.agency.controller;

import com.project.easywork.agency.dto.TeamDto;
import com.project.easywork.agency.service.TeamService;
import com.project.easywork.common.util.ApiResponseMessage;
import com.project.easywork.common.validator.ValidationUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Team", description = "측정대행업체 측정팀 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {
  
  private final TeamService teamService;
  
  @Operation(summary = "측정팀 등록 API", description = "새로운 측정팀 정보를 데이터베이스에 저장합니다.")
  @PostMapping()
  public ResponseEntity<ApiResponseMessage<String>> registerTeam(
      @Valid @RequestBody TeamDto request,
      BindingResult bindingResult
  ) {
    
    if (bindingResult.hasErrors()) {
      return ValidationUtils.handleBindingErrors(bindingResult);
    }
    
    teamService.register(request);
    
    return ResponseEntity.ok(new ApiResponseMessage<>(true, "생성 성공", null));
  }
  
  @Operation(summary = "측정팀 목록 조회 API", description = "전체 측정팀 목록을 조회합니다.")
  @GetMapping()
  public ResponseEntity<ApiResponseMessage<List<TeamDto>>> getTeams() {
    return ResponseEntity.ok(
        new ApiResponseMessage<>(true, "조회 성공", teamService.getList())
    );
  }
  
  @Operation(summary = "측정팀 조회 API", description = "해당 측정팀의 상세정보를 조회합니다.")
  @GetMapping("/{teamId}")
  public ResponseEntity<ApiResponseMessage<TeamDto>> getTeam(@PathVariable Long teamId) {
    return ResponseEntity.ok(new ApiResponseMessage<>(true, "조회 성공", teamService.get(teamId)));
  }
  
  @Operation(summary = "측정팀 수정 API", description = "해당 측정팀의 상세정보를 수정합니다.")
  @PatchMapping("/{teamId}")
  public ResponseEntity<ApiResponseMessage<TeamDto>> updateTeam
      (
          @PathVariable Long teamId,
          @Valid @RequestBody TeamDto request
      ) {
    
    request.setTeamId(teamId);
    teamService.update(request);
    
    return ResponseEntity.ok(
        new ApiResponseMessage<>(true, "수정 성공", null)
    );
  }
  
  @Operation(summary = "측정팀 삭제 API", description = "측정팀 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/{teamId}")
  public ResponseEntity<ApiResponseMessage<String>> removeTeam(@PathVariable Long teamId) {
    teamService.delete(teamId);
    return ResponseEntity.ok(
        new ApiResponseMessage<>(true, "삭제 성공", null)
    );
  }
}