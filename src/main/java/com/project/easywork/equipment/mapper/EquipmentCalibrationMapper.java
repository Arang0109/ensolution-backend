package com.project.easywork.equipment.mapper;

import com.project.easywork.equipment.domain.dto.EquipmentCalibrationCreateReqDto;
import com.project.easywork.equipment.domain.dto.EquipmentCalibrationResDto;
import com.project.easywork.equipment.domain.dto.EquipmentCalibrationUpdateReqDto;
import com.project.easywork.equipment.domain.persistance.Equipment;
import com.project.easywork.equipment.domain.persistance.EquipmentCalibration;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(), unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EquipmentCalibrationMapper {
  
  @Mapping(target = "equipmentId", source = "equipment.id")
  EquipmentCalibrationResDto toDto(EquipmentCalibration equipmentCalibration);
  
  @InheritInverseConfiguration
  EquipmentCalibration toEntity(EquipmentCalibrationCreateReqDto dto, Equipment equipment);
  
  List<EquipmentCalibrationResDto> toDtoList(List<EquipmentCalibration> equipmentCalibrations);
  
  void updateFromDto(
      EquipmentCalibrationUpdateReqDto dto,
      @MappingTarget EquipmentCalibration equipmentCalibration);
}
