package com.project.easywork.equipment.mapper;

import com.project.easywork.equipment.domain.dto.EquipmentCreateReqDto;
import com.project.easywork.equipment.domain.dto.EquipmentResDto;
import com.project.easywork.equipment.domain.dto.EquipmentUpdateReqDto;
import com.project.easywork.equipment.domain.persistance.Equipment;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring", builder = @Builder(),
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EquipmentMapper {
  
  EquipmentResDto toDto(Equipment equipment);
  
  Equipment toEntity(EquipmentCreateReqDto dto);
  
  List<EquipmentResDto> toDtoList(List<Equipment> equipments);
  
  void updateFromDto(
      EquipmentUpdateReqDto dto,
      @MappingTarget Equipment equipment
  );
}