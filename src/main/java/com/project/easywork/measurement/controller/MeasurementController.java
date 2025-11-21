package com.project.easywork.measurement.controller;

import com.project.easywork.measurement.dto.command.MeasurementCommandDto;
import com.project.easywork.measurement.dto.document.MeasurementDocument;
import com.project.easywork.measurement.service.MeasurementService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/measurement")
@RequiredArgsConstructor
public class MeasurementController {
  
  private final MeasurementService measurementService;
  
  @PostMapping
  public ResponseEntity<MeasurementDocument> createReport(
      @RequestBody MeasurementCommandDto request
  ) {
    MeasurementDocument saved = measurementService.processAndSave(request);
    return ResponseEntity.ok(saved);
  }
  
}
