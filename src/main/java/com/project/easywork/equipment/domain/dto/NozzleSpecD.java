package com.project.easywork.equipment.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record NozzleSpecD(
    List<NozzleDiameterSpecD> nozzleDiameters
) {
  public record NozzleDiameterSpecD(
     @NotNull @Positive BigDecimal diameter
  ) {}
}
