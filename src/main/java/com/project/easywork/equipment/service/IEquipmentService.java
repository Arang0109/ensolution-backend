package com.project.easywork.equipment.service;

import com.project.easywork.equipment.domain.dto.EquipmentCalibrationDateUpdateDto;
import com.project.easywork.equipment.domain.dto.EquipmentCreateReqDto;
import com.project.easywork.equipment.domain.dto.EquipmentResDto;
import com.project.easywork.equipment.domain.dto.EquipmentUpdateReqDto;

import java.util.List;

public interface IEquipmentService {
  List<EquipmentResDto> getList();
  EquipmentResDto getEquipment(Long equipmentId);
  EquipmentResDto register(EquipmentCreateReqDto dto);
  EquipmentResDto update(Long equipmentId, EquipmentUpdateReqDto dto);
  EquipmentResDto updateCalibrationDate(Long equipmentId, EquipmentCalibrationDateUpdateDto dto);
  void toggleAvailable(Long equipmentId);
  void delete(Long equipmentId);
}
