package com.project.easywork.schedule.controller;

import com.project.easywork.common.api.ApiResponse;
import com.project.easywork.schedule.domain.ScheduleStatus;
import com.project.easywork.schedule.domain.dto.*;
import com.project.easywork.schedule.service.IScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<ApiResponse<ScheduleResDto>> register(
      @Valid @RequestBody ScheduleCreateReqDto scheduleCreateReqDto
  ) {
    return ResponseEntity.ok().body(ApiResponse.success(scheduleService.register(scheduleCreateReqDto)));
  }
  
  @Operation(summary = "측정일정 목록 조회 API", description = "전체 측정일정 목록을 조회합니다.")
  @GetMapping()
  public ResponseEntity<ApiResponse<List<ScheduleTableViewDto>>> getList() {
    return ResponseEntity.ok().body(ApiResponse.success(scheduleService.getList()));
  }
  
  @Operation(summary = "측정시설 지난 일정 조회 API", description = "해당 측정시설에서 이미 완료된 측정일정 목록을 조회합니다.")
  @GetMapping("/stacks/{stackId}")
  public ResponseEntity<ApiResponse<List<ScheduleResDto>>> getListByStack(
      @PathVariable Long stackId,
      @RequestParam(required = false) List<ScheduleStatus> status) {
    return ResponseEntity.ok(ApiResponse.success(scheduleService.getListByStack(stackId, status)));
  }
  
  @Operation(summary = "측정일정 상세 조회 API", description = "해당 측정일정의 상세정보를 조회합니다.")
  @GetMapping("/{scheduleId}")
  public ResponseEntity<ApiResponse<ScheduleDetailResDto>> get(
      @PathVariable Long scheduleId
  ) {
    return ResponseEntity.ok().body(ApiResponse.success(scheduleService.getSchedule(scheduleId)));
  }
  
  @Operation(summary = "측정일정 수정 API", description = "해당 측정일정의 상세정보를 수정합니다.")
  @PatchMapping("/{scheduleId}")
  public ResponseEntity<ApiResponse<ScheduleResDto>> update
      (
          @PathVariable Long scheduleId,
          @Valid @RequestBody ScheduleUpdateReqDto request
      ) {
    
    return ResponseEntity.ok().body(ApiResponse.success(scheduleService.update(scheduleId, request)));
  }
  
  @Operation(summary = "측정항목 추가 API", description = "해당 측정일정의 측정항목을 추가합니다.")
  @PostMapping("/{scheduleId}/measurements")
  public ResponseEntity<ApiResponse<Void>> addMeasurement(
      @PathVariable Long scheduleId,
      @Valid @RequestBody List<ScheduleMeasurementCreateReqDto> request
  ) {
    scheduleService.addMeasurements(scheduleId, request);
    return ResponseEntity.ok().body(ApiResponse.success());
  }
  
  @Operation(summary = "측정상태 수정 API", description = "해당 측정일정의 측정상태를 수정합니다.")
  @PatchMapping("/{scheduleId}/status")
  public ResponseEntity<ApiResponse<ScheduleResDto>> updateStatus
      (
          @PathVariable Long scheduleId,
          @Valid @RequestBody ScheduleStatusUpdateReqDto request
      ) {
    
    return ResponseEntity.ok().body(ApiResponse.success(scheduleService.updateStatus(scheduleId, request)));
  }
  
  @Operation(summary = "측정일정 삭제 API", description = "측정일정 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/{scheduleId}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long scheduleId) {
    scheduleService.delete(scheduleId);
    return ResponseEntity.ok().body(ApiResponse.success("삭제 되었습니다.", null));
  }
}