package com.project.easywork.equipment.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ParticleSamplerSpecD(
    @NotNull @PositiveOrZero BigDecimal orificeDp,
    @NotNull @PositiveOrZero BigDecimal yd
) {
}
