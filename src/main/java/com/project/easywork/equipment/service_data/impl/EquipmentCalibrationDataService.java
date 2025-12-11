package com.project.easywork.equipment.service_data.impl;

import com.project.easywork.common.exception.CustomException;
import com.project.easywork.common.exception.ErrorCode;
import com.project.easywork.equipment.domain.persistance.EquipmentCalibration;
import com.project.easywork.equipment.repository.EquipmentCalibrationRepository;
import com.project.easywork.equipment.service_data.IEquipmentCalibrationDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EquipmentCalibrationDataService implements IEquipmentCalibrationDataService {
  
  private final EquipmentCalibrationRepository equipmentCalibrationRepository;
  
  @Override
  public EquipmentCalibration findById(Long equipmentCalibrationId) {
    return equipmentCalibrationRepository.findById(equipmentCalibrationId).orElseThrow(
        () -> new CustomException(ErrorCode.NOT_FOUND)
    );
  }
  
  @Override
  public EquipmentCalibration save(EquipmentCalibration equipmentCalibration) {
    return equipmentCalibrationRepository.save(equipmentCalibration);
  }
  
  @Override
  public void deleteById(Long equipmentCalibrationId) {
    equipmentCalibrationRepository.deleteById(equipmentCalibrationId);
  }
}
