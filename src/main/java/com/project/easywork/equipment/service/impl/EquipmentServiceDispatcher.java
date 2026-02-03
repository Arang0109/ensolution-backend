package com.project.easywork.equipment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.easywork.equipment.domain.EquipType;
import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.equipment.domain.dto.EquipmentCreateReqD;
import com.project.easywork.equipment.domain.dto.EquipmentUpdateReqD;
import com.project.easywork.equipment.service.IEquipmentService;
import com.project.easywork.equipment.service_data.IEquipmentDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EquipmentServiceDispatcher {
  private final List<IEquipmentService> services;
  
  private final IEquipmentDataService equipmentDataService;
  private final ObjectMapper objectMapper;
  
  public EquipmentDoc register(EquipmentCreateReqD dto) {
    System.out.println("dto: " + dto);
    return services.stream()
        .filter(s -> s.supportType() == dto.type())
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 장비 타입"))
        .register(dto);
  }
  
  public EquipmentDoc update(EquipmentUpdateReqD dto, String id) {
    EquipmentDoc doc = equipmentDataService.findById(id);
    
    doc.update(dto, objectMapper);
    
    return equipmentDataService.save(doc);
  }
  
  @Transactional(readOnly = true)
  public List<EquipmentDoc> getAllEquipments() {
   return equipmentDataService.findAll();
  }
  
  @Transactional(readOnly = true)
  public List<EquipmentDoc> getList(EquipType type) {
    return equipmentDataService.findByType(type);
  }
  
  public void deleteById(String equipmentId) {
    equipmentDataService.deleteById(equipmentId);
  }
}