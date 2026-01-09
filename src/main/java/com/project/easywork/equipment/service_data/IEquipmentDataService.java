package com.project.easywork.equipment.service_data;

import com.project.easywork.equipment.domain.EquipType;
import com.project.easywork.equipment.domain.persistance.Equipment;

import java.util.List;

public interface IEquipmentDataService {
  Equipment findById(Long equipmentId);
  List<Equipment> findAll();
  List<Equipment> findByType(EquipType type);
  Equipment save(Equipment equipment);
  void deleteById(Long equipmentId);
}