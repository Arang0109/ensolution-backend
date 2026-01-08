package com.project.easywork.equipment.mapper;

import com.project.easywork.equipment.domain.dto.EquipCreateD;
import com.project.easywork.equipment.domain.dto.EquipD;
import com.project.easywork.equipment.domain.dto.EquipUpdateD;
import com.project.easywork.equipment.domain.persistance.Equipment;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder()
)
public interface EquipmentMapper {
  EquipD toDto(Equipment equipment);
  
  Equipment toEntity(EquipCreateD dto);
  
  List<EquipD> toDtoList(List<Equipment> equipments);
}