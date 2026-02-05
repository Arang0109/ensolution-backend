package com.project.easywork.equipment.domain.dto.spec;

import com.project.easywork.equipment.domain.EquipType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record NozzleSpecD(
    List<NozzleDiameterSpecD> diameters
) implements EquipmentSpecReqD {
  public record NozzleDiameterSpecD(
    @NotNull
    EquipType type,
    @NotNull @Positive BigDecimal diameter
  ) {}
}
