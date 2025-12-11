package com.project.easywork.equipment.service.impl;

import com.project.easywork.equipment.domain.dto.EquipmentCreateReqDto;
import com.project.easywork.equipment.domain.dto.EquipmentResDto;
import com.project.easywork.equipment.domain.dto.EquipmentUpdateReqDto;
import com.project.easywork.equipment.domain.persistance.Equipment;
import com.project.easywork.equipment.mapper.EquipmentMapper;
import com.project.easywork.equipment.service.IEquipmentService;
import com.project.easywork.equipment.service_data.IEquipmentDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EquipmentService implements IEquipmentService {
  
  private final IEquipmentDataService equipmentDataService;
  private final EquipmentMapper equipmentMapper;
  
  @Override
  @Transactional(readOnly = true)
  public List<EquipmentResDto> getList() {
    return equipmentMapper.toDtoList(
        equipmentDataService.findAll()
    );
  }
  
  @Override
  @Transactional(readOnly = true)
  public EquipmentResDto getEquipment(Long equipmentId) {
    return equipmentMapper.toDto(
        equipmentDataService.findById(equipmentId)
    );
  }
  
  @Override
  public EquipmentResDto register(EquipmentCreateReqDto dto) {
    Equipment equipment = equipmentMapper.toEntity(dto);
    return equipmentMapper.toDto(
        equipmentDataService.save(equipment)
    );
  }
  
  @Override
  public EquipmentResDto update(Long equipmentId, EquipmentUpdateReqDto dto) {
    Equipment equipment = equipmentDataService.findById(equipmentId);
    equipmentMapper.updateFromDto(dto, equipment);
    return equipmentMapper.toDto(
        equipmentDataService.save(equipment)
    );
  }
  
  @Override
  public void toggleAvailable(Long equipmentId) {
    Equipment equipment = equipmentDataService.findById(equipmentId);
    equipment.toggleAvailable();
  }
  
  @Override
  public void delete(Long equipmentId) {
    equipmentDataService.deleteById(equipmentId);
  }
}
