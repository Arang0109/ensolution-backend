package com.project.easywork.equipment.service_data.impl;

import com.project.easywork.common.exception.CustomException;
import com.project.easywork.common.exception.ErrorCode;
import com.project.easywork.equipment.domain.persistance.Equipment;
import com.project.easywork.equipment.repository.EquipmentRepository;
import com.project.easywork.equipment.service_data.IEquipmentDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EquipmentDataService implements IEquipmentDataService {
  
  private final EquipmentRepository equipmentRepository;
  
  @Override
  public Equipment findById(Long equipmentId) {
    return equipmentRepository.findById(equipmentId).orElseThrow(
        () -> new CustomException(ErrorCode.NOT_FOUND, "해당 장비를 찾을 수 없습니다.")
    );
  }
  
  @Override
  public List<Equipment> findAll() {
    return equipmentRepository.findAll();
  }
  
  @Override
  public Equipment save(Equipment equipment) {
    return equipmentRepository.save(equipment);
  }
  
  @Override
  public void deleteById(Long equipmentId) {
    equipmentRepository.deleteById(equipmentId);
  }
}
