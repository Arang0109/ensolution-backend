package com.project.easywork.agency.controller;

import com.project.easywork.agency.dto.TeamDto;
import com.project.easywork.agency.service.TeamService;
import com.project.easywork.common.util.ApiResponseMessage;
import com.project.easywork.common.validator.ValidationUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {
  
  private final TeamService teamService;
  
  @Operation(summary = "측정팀 전체 조회")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "조회 성공"),
      @ApiResponse(responseCode = "401", description = "인증 실패"),
      @ApiResponse(responseCode = "404", description = "측정팀이 존재하지 않음")
  })
  @GetMapping()
  public ResponseEntity<ApiResponseMessage<List<TeamDto>>> getTeams() {
    return ResponseEntity.ok(
        new ApiResponseMessage<>(true, "조회 성공", teamService.getList())
    );
  }
  
  @Operation(summary = "측정팀 등록", description = "새로운 측정팀을 데이터베이스에 저장")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "생성 성공"),
      @ApiResponse(responseCode = "400", description = "요청 형식 오류")
  })
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
  
  @Operation(summary = "측정팀 단건 조회")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "조회 성공"),
      @ApiResponse(responseCode = "401", description = "인증 실패"),
      @ApiResponse(responseCode = "404", description = "측정팀이 존재하지 않음")
  })
  @GetMapping("/{teamId}")
  public ResponseEntity<ApiResponseMessage<TeamDto>> getTeam(@PathVariable Long teamId) {
    return ResponseEntity.ok(new ApiResponseMessage<>(true, "조회 성공", teamService.get(teamId)));
  }
  @Operation(summary = "측정팀 수정")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "수정 성공"),
      @ApiResponse(responseCode = "401", description = "인증 실패"),
      @ApiResponse(responseCode = "404", description = "측정팀이 존재하지 않음")
  })
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
  
  @Operation(summary = "측정팀 삭제")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "삭제 성공"),
      @ApiResponse(responseCode = "401", description = "인증 실패"),
      @ApiResponse(responseCode = "404", description = "측정팀이 존재하지 않음")
  })
  @DeleteMapping("/{teamId}")
  public ResponseEntity<ApiResponseMessage<String>> removeTeam(@PathVariable Long teamId) {
    teamService.delete(teamId);
    return ResponseEntity.ok(
        new ApiResponseMessage<>(true, "삭제 성공", null)
    );
  }
}