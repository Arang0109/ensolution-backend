package com.project.easywork.equipment.domain.dto;

import com.project.easywork.equipment.domain.EquipStatus;
import jakarta.validation.constraints.NotNull;

public record EquipmentStatusUpdateReq(
    @NotNull
    EquipStatus status
) {
}
