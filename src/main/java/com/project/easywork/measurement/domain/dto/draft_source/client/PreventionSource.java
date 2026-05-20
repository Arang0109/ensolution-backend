package com.project.easywork.measurement.domain.dto.draft_source.client;

import java.math.BigDecimal;
import java.util.List;

public record PreventionSource(
    Long preventionId,
    String name,
    List<FacilitySource> facilities,
    List<TargetSource> targets
) {
  public record FacilitySource(
      Long facilityId,
      String name,
      String fuelUsage,
      String itemOutput,
      String fuelInput,
      String fuelType
  ) {}
  public record TargetSource(
      Long targetId,
      String targetSubstance,
      BigDecimal removalEfficiency
  ) {}
}