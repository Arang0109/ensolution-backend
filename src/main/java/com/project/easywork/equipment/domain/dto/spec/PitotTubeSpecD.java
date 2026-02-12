package com.project.easywork.equipment.domain.dto.spec;

import com.project.easywork.equipment.domain.PitotTubeType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.List;

public record PitotTubeSpecD(
    @NotNull PitotTubeType pitotTubeType,
    List<PitotCoefficientSpecD> coefficients
) implements EquipmentSpecReqD {
  public record PitotCoefficientSpecD(
      @NotNull @PositiveOrZero BigDecimal coefficient,
      @NotNull @PositiveOrZero BigDecimal velocity
  ) {}
}
