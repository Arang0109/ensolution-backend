package com.project.easywork.equipment.service.impl;

import com.project.easywork.equipment.domain.EquipType;
import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.equipment.domain.dto.EquipmentCreateReqD;
import com.project.easywork.equipment.service.IEquipmentService;
import com.project.easywork.equipment.service_data.IEquipmentDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentFacade {
  private final List<IEquipmentService> services;
  
  private final IEquipmentDataService equipmentDataService;
  
  public EquipmentDoc register(EquipmentCreateReqD dto) {
    return services.stream()
        .filter(s -> s.supportType() == dto.type())
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 장비 타입"))
        .register(dto);
  }
  
  public List<EquipmentDoc> getAllEquipments() {
   return equipmentDataService.findAll();
  }
  
  public List<EquipmentDoc> getList(EquipType type) {
    return equipmentDataService.findByType(type);
  }
}