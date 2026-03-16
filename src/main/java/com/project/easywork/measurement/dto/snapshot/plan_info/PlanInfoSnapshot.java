package com.project.easywork.measurement.dto.snapshot.plan_info;

import com.project.easywork.plan.domain.MeasurementField;

import java.time.LocalDate;

public record PlanInfoSnapshot(
    String referenceNumber,
    LocalDate measureDate,
    MeasurementField measurementField,
    String measurementType,
    
    TeamSnapshot team,
    String vehicleNumber,
    String mentor,
    String mentee
) {
  public record TeamSnapshot(
      Long teamId,
      String name
  ) {}
}
