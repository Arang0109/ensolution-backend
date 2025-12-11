package com.project.easywork.equipment.service_data;

import com.project.easywork.equipment.domain.persistance.EquipmentCalibration;

public interface IEquipmentCalibrationDataService {
  EquipmentCalibration findById(Long equipmentCalibrationId);
  EquipmentCalibration save(EquipmentCalibration equipmentCalibration);
  void deleteById(Long equipmentCalibrationId);
}