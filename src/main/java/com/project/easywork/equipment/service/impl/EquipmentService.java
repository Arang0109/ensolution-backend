package com.project.easywork.equipment.service.impl;

import com.project.easywork.equipment.domain.EquipStatus;
import com.project.easywork.equipment.domain.EquipType;
import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.equipment.domain.document.spec.EquipmentSpec;
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
  private final SpecFactory specFactory;
  
  public EquipmentDoc register(EquipmentCreateReqD dto) {
    EquipmentSpec spec = specFactory.toSpec(dto.spec(), dto.type());
    
    EquipmentDoc doc = EquipmentDoc.createBase(dto)
        .toBuilder()
        .spec(spec)
        .build();
    return equipmentDataService.save(doc);
  }
  
  public EquipmentDoc update(EquipmentUpdateReqD dto, String id) {
    
    EquipmentDoc doc = equipmentDataService.findById(id);
    doc.updateCommonFields(dto);
    
    EquipmentSpec spec = specFactory.toSpec(dto.spec(), dto.type());
    
    doc.updateSpec(spec);
    
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
  
  public void changeStatus(String equipmentId, EquipStatus status) {
    EquipmentDoc doc = equipmentDataService.findById(equipmentId);
    doc.changeStatus(status);
    equipmentDataService.save(doc);
  }
}