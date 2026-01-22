package com.project.easywork.equipment.service_data.impl;

import com.project.easywork.equipment.domain.EquipType;
import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.equipment.repository.EquipmentRepository;
import com.project.easywork.equipment.service_data.IEquipmentDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EquipmentDataService implements IEquipmentDataService {
  
  private final EquipmentRepository equipmentRepository;
  
  @Override
  public EquipmentDoc save(EquipmentDoc doc) {
    return equipmentRepository.save(doc);
  }
  
  @Override
  public List<EquipmentDoc> findAll() {
    return equipmentRepository.findAll();
  }
  
  @Override
  public List<EquipmentDoc> findByType(EquipType type) {
    return equipmentRepository.findByType(type);
  }
}
