package com.project.easywork.schedule.controller;

import com.project.easywork.common.util.ApiResponse;
import com.project.easywork.common.util.ValidationUtils;
import com.project.easywork.schedule.domain.dto.*;
import com.project.easywork.schedule.service.IScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Schedule", description = "측정일정 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {
  
  private final IScheduleService scheduleService;
  
  @Operation(
      summary = "측정일정 등록 API",
      description = "새로운 측정일정 정보를 데이터베이스에 저장합니다."
  )
  @PostMapping()
  public ResponseEntity<ApiResponse<ScheduleResponseDto>> registerSchedule(
      @Valid @RequestBody ScheduleCreateRequestDto request,
      BindingResult bindingResult
  ) {
    if (bindingResult.hasErrors()) {
      return ValidationUtils.handleBindingErrors(bindingResult);
    }
    return ResponseEntity.ok().body(ApiResponse.ok(scheduleService.register(request)));
  }
  
  @Operation(summary = "측정일정 목록 조회 API", description = "전체 측정일정 목록을 조회합니다.")
  @GetMapping()
  public ResponseEntity<ApiResponse<List<ScheduleTableViewDto>>> getPollutants() {
    return ResponseEntity.ok().body(ApiResponse.ok(scheduleService.getList()));
  }
  
  @Operation(summary = "측정일정 상세 조회 API", description = "해당 측정일정의 상세정보를 조회합니다.")
  @GetMapping("/{scheduleId}")
  public ResponseEntity<ApiResponse<ScheduleDetailResponseDto>> getSchedule(
      @PathVariable Long scheduleId
  ) {
    return ResponseEntity.ok().body(ApiResponse.ok(scheduleService.getSchedule(scheduleId)));
  }
  
  @Operation(summary = "측정일정 수정 API", description = "해당 측정일정의 상세정보를 수정합니다.")
  @PatchMapping("/{scheduleId}")
  public ResponseEntity<ApiResponse<ScheduleResponseDto>> updateSchedule
      (
          @PathVariable Long scheduleId,
          @Valid @RequestBody ScheduleUpdateRequestDto request
      ) {
    
    return ResponseEntity.ok(ApiResponse.ok(scheduleService.update(scheduleId, request)));
  }
  
  @Operation(summary = "측정상태 수정 API", description = "해당 측정일정의 측정상태를 수정합니다.")
  @PatchMapping("/{scheduleId}/status")
  public ResponseEntity<ApiResponse<ScheduleResponseDto>> updateSchedule
      (
          @PathVariable Long scheduleId,
          @Valid @RequestBody ScheduleStatusUpdateRequestDto request
      ) {
    
    return ResponseEntity.ok(ApiResponse.ok(scheduleService.updateStatus(scheduleId, request)));
  }
  
  @Operation(summary = "측정일정 삭제 API", description = "측정일정 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/{scheduleId}")
  public ResponseEntity<ApiResponse<Void>> removeCompany(@PathVariable Long scheduleId) {
    scheduleService.delete(scheduleId);
    return ResponseEntity.ok(ApiResponse.ok());
  }
}
