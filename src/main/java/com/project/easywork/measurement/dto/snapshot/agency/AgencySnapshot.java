package com.project.easywork.measurement.dto.snapshot.agency;

import com.project.easywork.plan.domain.MeasurementField;

import java.time.LocalDate;

public record AgencySnapshot(
    String referenceNumber,
    LocalDate measureDate,
    MeasurementField measurementField,
    String measurementType,
    boolean simplifiedMeasurement,
    
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
