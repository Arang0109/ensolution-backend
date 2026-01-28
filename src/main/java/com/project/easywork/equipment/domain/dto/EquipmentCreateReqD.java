package com.project.easywork.equipment.domain.dto;

import com.project.easywork.equipment.domain.EquipType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EquipmentCreateReqD(
    
    @NotNull
    EquipType type,
    
    String managementNumber,
    String serialNumber,
    String modelName,
    String equipmentName,
    String alias,
    
    @PositiveOrZero BigDecimal price,
    String manufacturer,
    String originCountry,
    @PastOrPresent LocalDate purchaseDate,
    String remark,
    
    @Positive Integer calibrationCycle,
    
    Object spec
) {}