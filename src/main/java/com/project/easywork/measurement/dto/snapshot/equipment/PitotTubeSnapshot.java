package com.project.easywork.measurement.dto.snapshot.equipment;

import com.project.easywork.equipment.domain.PitotTubeType;

import java.math.BigDecimal;
import java.util.List;

public record PitotTubeSnapshot(
    String equipmentId,
    String managementNumber,
    String alias,
    PitotTubeType pitotTubeType,
    
    List<PitotCoefficientSnapshot> coefficients
) {
  public record PitotCoefficientSnapshot(
      BigDecimal velocity,
      BigDecimal coefficient
  ) {}
}