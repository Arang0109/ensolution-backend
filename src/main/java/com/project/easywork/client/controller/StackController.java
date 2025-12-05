package com.project.easywork.client.controller;

import com.project.easywork.client.domain.dto.stack.StackCreateRequestDto;
import com.project.easywork.client.domain.dto.stack.StackDetailResponseDto;
import com.project.easywork.client.domain.dto.stack.StackResponseDto;
import com.project.easywork.client.domain.dto.stack.StackUpdateRequestDto;
import com.project.easywork.common.constant.ScheduleStatus;
import com.project.easywork.common.util.ApiResponse;
import com.project.easywork.client.service.IStackService;
import com.project.easywork.common.util.ValidationUtils;
import com.project.easywork.schedule.domain.dto.ScheduleResponseDto;
import com.project.easywork.schedule.service.IScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Stack", description = "측정시설 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/stacks")
@RequiredArgsConstructor
public class StackController {
  
  private final IStackService stackService;
  private final IScheduleService scheduleService;
  
  @Operation(summary = "측정시설 등록 API", description = "새로운 측정시설 정보를 데이터베이스에 저장합니다.")
  @PostMapping()
  public ResponseEntity<ApiResponse<Void>> registerStack
      (
          @Valid @RequestBody StackCreateRequestDto request,
          BindingResult bindingResult
      ) {
    if (bindingResult.hasErrors()) {
      return ValidationUtils.handleBindingErrors(bindingResult);
    }
    System.out.println("controller");
    System.out.println("request: " + request);
    stackService.registerStack(request);
    return ResponseEntity.ok().body(ApiResponse.ok());
  }
  
  @Operation(summary = "측정시설 목록 조회 API", description = "전체 측정시설 목록을 조회합니다.")
  @GetMapping()
  public ResponseEntity<ApiResponse<List<StackResponseDto>>> getStacks() {
    return ResponseEntity.ok().body(ApiResponse.ok(stackService.getStacks()));
  }
  
  @Operation(summary = "측정시설 조회 API", description = "해당 측정시설의 상세정보를 조회합니다.")
  @GetMapping("/{stackId}")
  public ResponseEntity<ApiResponse<StackDetailResponseDto>> getStack(@PathVariable Long stackId) {
    return ResponseEntity.ok().body(ApiResponse.ok(stackService.getStack(stackId)));
  }
  
  @Operation(summary = "측정시설 지난 일정 조회 API", description = "해당 측정시설에서 이미 완료된 측정일정 목록을 조회합니다.")
  @GetMapping("/stacks/{stackId}/schedules")
  public ResponseEntity<ApiResponse<List<ScheduleResponseDto>>> getSchedulesByStack(
      @PathVariable Long stackId,
      @RequestParam(required = false) List<ScheduleStatus> status) {
    return ResponseEntity.ok(ApiResponse.ok(scheduleService.getListByStack(stackId, status)));
  }
  
  @Operation(summary = "측정시설 수정 API", description = "해당 측정시설의 상세정보를 수정합니다.")
  @PatchMapping("/{stackId}")
  public ResponseEntity<ApiResponse<StackResponseDto>> updateStack
      (
          @PathVariable Long stackId,
          @Valid @RequestBody StackUpdateRequestDto request
      ) {
    return ResponseEntity.ok().body(ApiResponse.ok(stackService.updateStack(stackId, request)));
  }
  
  @Operation(summary = "측정시설 삭제 API", description = "측정시설 정보를 데이터베이스에서 삭제합니다.")
  @DeleteMapping("/{stackId}")
  public ResponseEntity<ApiResponse<Void>> removeStack(@PathVariable Long stackId) {
    stackService.removeStack(stackId);
    return ResponseEntity.ok(ApiResponse.ok());
  }
  
  
//  @Operation(summary = "엑셀 내보내기 API", description = "측정시설 정보에 관련된 엑셀 파일을 생성하고 내보냅니다.")
//  @PostMapping("/export/excel")
//  public ResponseEntity<byte[]> exportReportExcel(@RequestBody MeasurementReportExportDto request) throws Exception {
//    HyundaiReportExporter exporter = new HyundaiReportExporter();
//    byte[] fileData = exporter.export(request);
//
//    String fileName = exporter.getFileName(request);
//
//    String encodedFilename = java.net.URLEncoder.encode(fileName, StandardCharsets.UTF_8)
//        .replaceAll("\\+", "%20");
//
//    String contentDisposition = "attachment; filename*=UTF-8''" + encodedFilename;
//
//    return ResponseEntity.ok()
//        .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
//        .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Content-Disposition, Content-Type")
//        .contentType(MediaType.parseMediaType(
//            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
//        .body(fileData);
//  }
}
