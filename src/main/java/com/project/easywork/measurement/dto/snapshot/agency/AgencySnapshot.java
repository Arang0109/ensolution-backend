package com.project.easywork.measurement.dto.snapshot.agency;

public record AgencySnapshot(
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
