package com.project.easywork.equipment.domain.dto;

import com.project.easywork.equipment.domain.dto.spec.EquipmentSpecReqD;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EquipmentCreateReqD(
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
    
    EquipmentSpecReqD spec
) {}