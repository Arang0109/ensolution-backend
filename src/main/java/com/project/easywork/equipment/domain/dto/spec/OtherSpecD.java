package com.project.easywork.equipment.domain.dto.spec;

import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record OtherSpecD(
    @PositiveOrZero BigDecimal totalVolume
) implements EquipmentSpecReqD {
}