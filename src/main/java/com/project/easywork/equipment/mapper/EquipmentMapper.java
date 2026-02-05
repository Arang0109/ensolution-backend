package com.project.easywork.equipment.mapper;

import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.equipment.domain.dto.EquipmentCreateReqD;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EquipmentMapper {
  public static EquipmentDoc toDocBase(EquipmentCreateReqD dto) {
    return EquipmentDoc.builder()
        .managementNumber(dto.managementNumber())
        .serialNumber(dto.serialNumber())
        .modelName(dto.modelName())
        .equipmentName(dto.equipmentName())
        .alias(dto.alias())
        .price(dto.price())
        .manufacturer(dto.manufacturer())
        .originCountry(dto.originCountry())
        .purchaseDate(dto.purchaseDate())
        .remark(dto.remark())
        .calibrationCycle(dto.calibrationCycle())
        .lastCalibrationDate(null)
        .build();
  }
}
