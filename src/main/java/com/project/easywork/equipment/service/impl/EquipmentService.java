package com.project.easywork.equipment.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.easywork.equipment.domain.EquipType;
import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.equipment.domain.document.spec.*;
import com.project.easywork.equipment.domain.dto.EquipmentCreateReqD;
import com.project.easywork.equipment.domain.dto.EquipmentUpdateReqD;
import com.project.easywork.equipment.service_data.IEquipmentDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EquipmentService {
  private final IEquipmentDataService equipmentDataService;
  private final ObjectMapper objectMapper;
  
  public EquipmentDoc register(EquipmentCreateReqD dto) {
    EquipmentSpec spec = objectMapper.convertValue(dto.spec(), EquipmentSpec.class);
    
    EquipmentDoc doc = EquipmentDoc.createBase(dto)
        .toBuilder()
        .spec(spec)
        .build();
    return equipmentDataService.save(doc);
  }
  
  public EquipmentDoc update(EquipmentUpdateReqD dto, String id) {
    
    EquipmentDoc doc = equipmentDataService.findById(id);
    doc.updateCommonFields(dto);
    
    if (dto.spec() != null) {
      EquipmentSpec spec = objectMapper.convertValue(dto.spec(), EquipmentSpec.class);
      doc.updateSpec(spec);
    }
    
    return equipmentDataService.save(doc);
  }
  
  public EquipmentDoc getEquipment(String id) {
    return equipmentDataService.findById(id);
  }
  
  @Transactional(readOnly = true)
  public List<EquipmentDoc> getList(EquipType type) {
    if (type == null) {
      return equipmentDataService.findAll();
    }
    return equipmentDataService.findByType(type);
  }
  
  public void deleteById(String equipmentId) {
    equipmentDataService.deleteById(equipmentId);
  }
}