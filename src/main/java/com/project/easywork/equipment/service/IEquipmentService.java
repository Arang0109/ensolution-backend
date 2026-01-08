package com.project.easywork.equipment.service;

import com.project.easywork.equipment.domain.dto.EquipCalibrationDateUpdateD;
import com.project.easywork.equipment.domain.dto.EquipCreateD;
import com.project.easywork.equipment.domain.dto.EquipD;
import com.project.easywork.equipment.domain.dto.EquipUpdateD;

import java.util.List;

public interface IEquipmentService {
  List<EquipD> getList();
  List<EquipD> getListByParticular();
  EquipD getEquipment(Long equipmentId);
  EquipD register(EquipCreateD dto);
  EquipD update(Long equipmentId, EquipUpdateD dto);
  EquipD updateCalibrationDate(Long equipmentId, EquipCalibrationDateUpdateD dto);
  void toggleAvailable(Long equipmentId);
  void delete(Long equipmentId);
}
