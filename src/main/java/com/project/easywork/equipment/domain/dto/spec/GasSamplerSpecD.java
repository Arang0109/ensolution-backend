package com.project.easywork.equipment.domain.dto.spec;

import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record GasSamplerSpecD(
    @PositiveOrZero BigDecimal totalVolume
) implements EquipmentSpecReqD {
}
