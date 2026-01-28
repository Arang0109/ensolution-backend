package com.project.easywork.equipment.domain.dto;

import com.project.easywork.equipment.domain.PitotTubeType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.List;

public record PitotTubeSpecD(
    @NotNull PitotTubeType type,
    List<CoefficientSpecD> coefficients
) {
  public record CoefficientSpecD(
      @NotNull @PositiveOrZero BigDecimal coefficient,
      @NotNull @PositiveOrZero BigDecimal velocity
  ) {}
}
