package com.project.easywork.equipment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.easywork.equipment.domain.EquipType;
import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.equipment.domain.document.spec.GasSamplerSpecDoc;
import com.project.easywork.equipment.domain.dto.EquipmentCreateReqD;
import com.project.easywork.equipment.service.IEquipmentService;
import com.project.easywork.equipment.service_data.IEquipmentDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class GasSamplerService implements IEquipmentService {
  
  private final IEquipmentDataService equipmentDataService;
  private final ObjectMapper objectMapper;
  
  @Override
  public EquipType supportType() {
    return EquipType.GAS_SAMPLER;
  }
  
  @Override
  public EquipmentDoc register(EquipmentCreateReqD dto) {
    GasSamplerSpecDoc spec = parseSpec(dto);
    
    EquipmentDoc doc = buildEquipment(dto, spec);
    return equipmentDataService.save(doc);
  }
  
  private GasSamplerSpecDoc parseSpec(EquipmentCreateReqD dto) {
    return objectMapper.convertValue(dto.spec(), GasSamplerSpecDoc.class);
  }
  
  private EquipmentDoc buildEquipment(EquipmentCreateReqD dto, GasSamplerSpecDoc spec) {
    return EquipmentDoc.builder()
        .type(dto.type())
        .managementNumber(dto.managementNumber())
        .serialNumber(dto.serialNumber())
        .modelName(dto.modelName())
        .alias(dto.alias())
        .price(dto.price())
        .originCountry(dto.originCountry())
        .purchaseDate(dto.purchaseDate())
        .remark(dto.remark())
        .calibrationCycle(dto.calibrationCycle())
        .spec(spec)
        .build();
  }
}