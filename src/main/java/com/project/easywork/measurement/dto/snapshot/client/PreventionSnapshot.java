package com.project.easywork.measurement.dto.snapshot.client;

import java.math.BigDecimal;
import java.util.List;

public record PreventionSnapshot(
    Long preventionId,
    String name,
    List<FacilitySnapshot> facilities,
    List<TargetSnapshot> targets
) {
  public record FacilitySnapshot(
      Long facilityId,
      String name,
      String fuelUsage,
      String itemOutput,
      String fuelInput,
      String fuelType
  ) {}
  public record TargetSnapshot(
      Long targetId,
      String targetSubstance,
      BigDecimal removalEfficiency
  ) {}
}