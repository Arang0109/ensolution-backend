package com.project.easywork.equipment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.easywork.equipment.domain.EquipType;
import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.equipment.domain.document.spec.PitotTubeSpecDoc;
import com.project.easywork.equipment.domain.dto.EquipmentCreateReqD;
import com.project.easywork.equipment.service.IEquipmentService;
import com.project.easywork.equipment.service_data.IEquipmentDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PitotTubeService implements IEquipmentService {
  
  private final IEquipmentDataService equipmentDataService;
  private final ObjectMapper objectMapper;
  
  @Override
  public EquipType supportType() {
    return EquipType.PITOT_TUBE;
  }
  
  @Override
  public EquipmentDoc register(EquipmentCreateReqD dto) {
    PitotTubeSpecDoc spec = parseSpec(dto);
    validate(spec);
    
    EquipmentDoc doc = buildEquipment(dto, spec);
    return equipmentDataService.save(doc);
  }
  
  private PitotTubeSpecDoc parseSpec(EquipmentCreateReqD dto) {
    return objectMapper.convertValue(dto.spec(), PitotTubeSpecDoc.class);
  }
  
  private void validate(PitotTubeSpecDoc spec) {
    if (spec.getCoefficients() == null || spec.getCoefficients().isEmpty()) {
      throw new IllegalArgumentException("피토우관 계수는 최소 1개 이상 필요합니다.");
    }
  }
  
  private EquipmentDoc buildEquipment(EquipmentCreateReqD dto, PitotTubeSpecDoc spec) {
    return EquipmentDoc.builder()
        .type(dto.type())
        .managementNumber(dto.managementNumber())
        .serialNumber(dto.serialNumber())
        .modelName(dto.modelName())
        .equipmentName(dto.equipmentName())
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
