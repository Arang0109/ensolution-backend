package com.project.easywork.measurement.dto.snapshot.agency;

import java.time.LocalDate;

public record AgencySnapshot(
    LocalDate measureDate,
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
