package com.project.easywork.measurement.controller;

import com.project.easywork.common.util.ApiResponse;
import com.project.easywork.measurement.dto.MeasurementDraftUpdateRequest;
import com.project.easywork.measurement.dto.command.MeasurementCommandDto;
import com.project.easywork.measurement.dto.document.MeasurementDocument;
import com.project.easywork.measurement.service.MeasurementService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/measurement")
@RequiredArgsConstructor
public class MeasurementController {
  
  private final MeasurementService measurementService;
  
  @PostMapping
  public ResponseEntity<ApiResponse<MeasurementDocument>> createReport(
      @RequestBody MeasurementCommandDto request
  ) {
    return ResponseEntity.ok().body(ApiResponse.ok(measurementService.processAndSave(request)));
  }
  
  @PatchMapping("/{objectId}/draft")
  public ResponseEntity<ApiResponse<Void>> updateDraft(
      @PathVariable String objectId,
      @RequestBody MeasurementDraftUpdateRequest request
  ) {
    measurementService.updateDraft(objectId, request);
    return ResponseEntity.noContent().build();
  }
  
}
