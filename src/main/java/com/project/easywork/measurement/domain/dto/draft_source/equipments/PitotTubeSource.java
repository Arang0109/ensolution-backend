package com.project.easywork.measurement.domain.dto.draft_source.equipments;

import com.project.easywork.equipment.domain.PitotTubeType;

import java.math.BigDecimal;
import java.util.List;

public record PitotTubeSource(
    String equipmentId,
    String managementNumber,
    String alias,
    PitotTubeType pitotTubeType,
    
    List<PitotCoefficientSource> coefficients
) {
  public record PitotCoefficientSource(
      BigDecimal velocity,
      BigDecimal coefficient
  ) {}
}