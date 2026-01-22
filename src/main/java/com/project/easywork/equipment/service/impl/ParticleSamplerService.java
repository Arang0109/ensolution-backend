package com.project.easywork.equipment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.easywork.equipment.domain.EquipType;
import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.equipment.domain.document.spec.ParticleSamplerSpecDoc;
import com.project.easywork.equipment.domain.dto.EquipmentCreateReqD;
import com.project.easywork.equipment.service.IEquipmentService;
import com.project.easywork.equipment.service_data.IEquipmentDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ParticleSamplerService implements IEquipmentService {
  
  private final IEquipmentDataService equipmentDataService;
  private final ObjectMapper objectMapper;
  
  @Override
  public EquipType supportType() {
    return EquipType.PARTICLE_SAMPLER;
  }
  
  @Override
  public EquipmentDoc register(EquipmentCreateReqD dto) {
    ParticleSamplerSpecDoc spec = parseSpec(dto);
    
    EquipmentDoc doc = buildEquipment(dto, spec);
    return equipmentDataService.save(doc);
  }
  
  private ParticleSamplerSpecDoc parseSpec(EquipmentCreateReqD dto) {
    return objectMapper.convertValue(dto.spec(), ParticleSamplerSpecDoc.class);
  }
  
  private EquipmentDoc buildEquipment(EquipmentCreateReqD dto, ParticleSamplerSpecDoc spec) {
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
