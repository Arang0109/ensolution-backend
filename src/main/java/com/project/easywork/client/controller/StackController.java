package com.project.easywork.client.controller;

import com.project.easywork.common.util.ApiResponse;
//import com.project.easywork.common.excel.HyundaiReportExporter;
import com.project.easywork.common.excel.dto.MeasurementReportExportDto;
import com.project.easywork.client.domain.dto.stack.StackDetailDto;
import com.project.easywork.client.service.StackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@Tag(name = "Stack", description = "측정시설 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/stacks")
@RequiredArgsConstructor
public class StackController {
  
  private final StackService stackService;
  
  @Operation(summary = "측정시설 조회 API", description = "해당 측정시설의 상세정보를 조회합니다.")
  @GetMapping("/{stackId}")
  public ResponseEntity<ApiResponse<StackDetailDto>> getStacks
      (
          @PathVariable Long stackId
      ) {

    return ResponseEntity.ok(
        new ApiResponse<>(true, "조회 성공", stackService.getStack(stackId))
    );
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
