package com.project.easywork.equipment.domain.dto.spec;

import com.project.easywork.equipment.domain.EquipType;
import jakarta.validation.constraints.NotNull;

public record OtherSpecD(
    @NotNull
    EquipType type
) implements EquipmentSpecReqD {
}
