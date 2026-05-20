package com.project.easywork.pollutant.controller;

import com.project.easywork.common.web.ApiResponse;
import com.project.easywork.pollutant.domain.dto.PollutantCreateD;
import com.project.easywork.pollutant.domain.dto.PollutantD;
import com.project.easywork.pollutant.domain.dto.PollutantUpdateD;
import com.project.easywork.pollutant.service.IPollutantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pollutant", description = "대기오염물질 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/pollutants")
@RequiredArgsConstructor
public class PollutantController {
  
  private final IPollutantService pollutantService;
  
  @Operation(
      summary = "대기오염물질 등록 API",
      description = "새로운 대기오염물질 정보를 데이터베이스에 저장합니다."
  )
  @PostMapping()
  public ResponseEntity<ApiResponse<PollutantD>> register(
      @Valid @RequestBody PollutantCreateD request
  ) {
    return ResponseEntity.ok().body(ApiResponse.success(pollutantService.registerPollutant(request)));
  }
  
  @Operation(summary = "대기오염물질 목록 조회 API", description = "전체 대기오염물질 목록을 조회합니다.")
  @GetMapping()
  public ResponseEntity<ApiResponse<List<PollutantD>>> getList() {
    return ResponseEntity.ok().body(ApiResponse.success(pollutantService.getPollutants()));
  }
  
  @Operation(summary = "대기오염물질 조회 API", description = "해당 대기오염물질의 상세정보를 조회합니다.")
  @GetMapping("/{pollutantId}")
  public ResponseEntity<ApiResponse<PollutantD>> get(@PathVariable Long pollutantId) {
    return ResponseEntity.ok().body(ApiResponse.success(pollutantService.getPollutant(pollutantId)));
  }
  
  @Operation(summary = "대기오염물질 수정 API", description = "해당 대기오염물질의 상세정보를 수정합니다.")
  @PatchMapping("/{pollutantId}")
  public ResponseEntity<ApiResponse<PollutantD>> update
      (
          @PathVariable Long pollutantId,
          @Valid @RequestBody PollutantUpdateD request
      ) {
    
    return ResponseEntity.ok(ApiResponse.success(pollutantService.updatePollutant(pollutantId, request)));
  }
  
  @Operation(summary = "대기오염물질 삭제 API", description = "대기오염물질 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/{pollutantId}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long pollutantId) {
    pollutantService.removePollutant(pollutantId);
    return ResponseEntity.ok(ApiResponse.success());
  }
}
