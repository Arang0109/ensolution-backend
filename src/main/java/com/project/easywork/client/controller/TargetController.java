package com.project.easywork.client.controller;

import com.project.easywork.client.domain.dto.target.TargetCreateRequestDto;
import com.project.easywork.client.domain.dto.target.TargetResponseDto;
import com.project.easywork.client.domain.dto.target.TargetUpdateRequestDto;
import com.project.easywork.client.service.ITargetService;
import com.project.easywork.common.util.ApiResponse;
import com.project.easywork.common.validator.ValidationUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Targets", description = "제거대상물질 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/targets")
@RequiredArgsConstructor
public class TargetController {
  
  private final ITargetService targetService;
  
  @Operation(summary = "제거대상물질 등록 API", description = "새로운 제거대상물질 정보를 데이터베이스에 저장합니다.")
  @PostMapping()
  public ResponseEntity<ApiResponse<Void>> registerTarget
      (
          @Valid @RequestBody TargetCreateRequestDto request,
          BindingResult bindingResult
      ) {
    if (bindingResult.hasErrors()) {
      return ValidationUtils.handleBindingErrors(bindingResult);
    }
    targetService.registerTarget(request);
    return ResponseEntity.ok().body(ApiResponse.ok());
  }
  
  @Operation(summary = "제거대상물질 수정 API", description = "해당 제거대상물질의 상세정보를 수정합니다.")
  @PatchMapping("/{targetId}")
  public ResponseEntity<ApiResponse<TargetResponseDto>> updateTarget
      (
          @PathVariable Long targetId,
          @Valid @RequestBody TargetUpdateRequestDto request
      ) {
    return ResponseEntity.ok().body(ApiResponse.ok(targetService.updateTarget(targetId, request)));
  }
  
  @Operation(summary = "제거대상물질 삭제 API", description = "제거대상물질 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/{targetId}")
  public ResponseEntity<ApiResponse<Void>> removeTarget(@PathVariable Long targetId) {
    targetService.removeTarget(targetId);
    return ResponseEntity.ok(ApiResponse.ok());
  }
}
