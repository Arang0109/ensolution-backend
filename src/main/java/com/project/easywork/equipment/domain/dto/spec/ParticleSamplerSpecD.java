package com.project.easywork.equipment.domain.dto.spec;

import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ParticleSamplerSpecD(
    @PositiveOrZero BigDecimal totalVolume,
    @PositiveOrZero BigDecimal orificeDp,
    @PositiveOrZero BigDecimal yd
) implements EquipmentSpecReqD {
}
