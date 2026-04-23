package com.project.easywork.report.controller;

import com.project.easywork.report.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@Tag(name = "Report", description = "성적서 관련 API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {
  private final ReportService reportService;
  
  @Operation(summary = "성적서 다운로드 API", description = "데이터를 저장합니다.")
  @GetMapping("/{planId}/download")
  public ResponseEntity<ByteArrayResource> downloadReport(@PathVariable Long planId) {
    ReportService.DownloadFile file = reportService.createReportZip(planId);
    
    ByteArrayResource resource = new ByteArrayResource(file.getData());
    
    return ResponseEntity.ok()
        .header(
            HttpHeaders.CONTENT_DISPOSITION,
            ContentDisposition.attachment()
                .filename(file.getFileName(), StandardCharsets.UTF_8)
                .build()
                .toString()
        )
        .contentType(MediaType.parseMediaType(file.getContentType()))
        .contentLength(file.getData().length)
        .body(resource);
  }
}