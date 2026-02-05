package com.project.easywork.equipment.domain.dto.spec;

import com.project.easywork.equipment.domain.EquipType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ParticleSamplerSpecD(
    @NotNull
    EquipType type,
    @PositiveOrZero BigDecimal totalVolume,
    @PositiveOrZero BigDecimal orificeDp,
    @PositiveOrZero BigDecimal yd
) implements EquipmentSpecReqD {
}
