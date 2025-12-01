package com.project.easywork.pollutant.controller;

import com.project.easywork.common.util.ApiResponse;
import com.project.easywork.common.util.ValidationUtils;
import com.project.easywork.pollutant.domain.dto.PollutantCreateRequestDto;
import com.project.easywork.pollutant.domain.dto.PollutantResponseDto;
import com.project.easywork.pollutant.domain.dto.PollutantUpdateRequestDto;
import com.project.easywork.pollutant.service.IPollutantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pollutant", description = "측정물질 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/pollutants")
@RequiredArgsConstructor
public class PollutantController {
  
  private final IPollutantService pollutantService;
  
  @Operation(summary = "측정물질 등록 API", description = "새로운 측정물질 정보를 데이터베이스에 저장합니다.")
  @PostMapping()
  public ResponseEntity<ApiResponse<PollutantResponseDto>> registerPollutant(
      @Valid @RequestBody PollutantCreateRequestDto request,
      BindingResult bindingResult
  ) {
    if (bindingResult.hasErrors()) {
      return ValidationUtils.handleBindingErrors(bindingResult);
    }
    return ResponseEntity.ok().body(ApiResponse.ok(pollutantService.registerPollutant(request)));
  }
  
  @Operation(summary = "측정물질 목록 조회 API", description = "전체 측정물질 목록을 조회합니다.")
  @GetMapping()
  public ResponseEntity<ApiResponse<List<PollutantResponseDto>>> getPollutants() {
    return ResponseEntity.ok().body(ApiResponse.ok(pollutantService.getPollutants()));
  }
  
  @Operation(summary = "측정물질 조회 API", description = "해당 측정물질의 상세정보를 조회합니다.")
  @GetMapping("/{pollutantId}")
  public ResponseEntity<ApiResponse<PollutantResponseDto>> getPollutant(@PathVariable Long pollutantId) {
    return ResponseEntity.ok().body(ApiResponse.ok(pollutantService.getPollutant(pollutantId)));
  }
  
  @Operation(summary = "측정물질 수정 API", description = "해당 측정물질의 상세정보를 수정합니다.")
  @PatchMapping("/{pollutantId}")
  public ResponseEntity<ApiResponse<PollutantResponseDto>> updatePollutant
      (
          @PathVariable Long pollutantId,
          @Valid @RequestBody PollutantUpdateRequestDto request
      ) {
    
    return ResponseEntity.ok(ApiResponse.ok(pollutantService.updatePollutant(pollutantId, request)));
  }
  
  @Operation(summary = "측정물질 삭제 API", description = "측정물질 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/{pollutantId}")
  public ResponseEntity<ApiResponse<Void>> removeCompany(@PathVariable Long pollutantId) {
    pollutantService.removePollutant(pollutantId);
    return ResponseEntity.ok(ApiResponse.ok());
  }
}
