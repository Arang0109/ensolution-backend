package com.project.easywork.equipment.service.impl;

import com.project.easywork.equipment.domain.EquipType;
import com.project.easywork.equipment.domain.dto.EquipCalibrationDateUpdateD;
import com.project.easywork.equipment.domain.dto.EquipCreateD;
import com.project.easywork.equipment.domain.dto.EquipD;
import com.project.easywork.equipment.domain.dto.EquipUpdateD;
import com.project.easywork.equipment.domain.persistance.Equipment;
import com.project.easywork.equipment.mapper.EquipmentMapper;
import com.project.easywork.equipment.service.IEquipmentService;
import com.project.easywork.equipment.service_data.IEquipmentDataService;
import jakarta.persistence.EntityManager;
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
  
  private final EntityManager entityManager;
  
  @Override
  @Transactional(readOnly = true)
  public List<EquipD> getList() {
    return equipmentMapper.toDtoList(
        equipmentDataService.findAll()
    );
  }
  
  @Override
  public List<EquipD> getListByParticular() {
    return equipmentMapper.toDtoList(
        equipmentDataService.findByType(EquipType.PARTICULAR)
    );
  }
  
  @Override
  @Transactional(readOnly = true)
  public EquipD getEquipment(Long equipmentId) {
    return equipmentMapper.toDto(
        equipmentDataService.findById(equipmentId)
    );
  }
  
  @Override
  public EquipD register(EquipCreateD dto) {
    Equipment equipment = equipmentMapper.toEntity(dto);
    return equipmentMapper.toDto(
        equipmentDataService.save(equipment)
    );
  }
  
  @Override
  public EquipD update(Long equipmentId, EquipUpdateD dto) {
    Equipment equipment = equipmentDataService.findById(equipmentId);
    equipment.update(dto);
    
    entityManager.flush();
    
    return equipmentMapper.toDto(equipment);
  }
  
  @Override
  public EquipD updateCalibrationDate(Long equipmentId, EquipCalibrationDateUpdateD dto) {
    Equipment equipment = equipmentDataService.findById(equipmentId);
    equipment.updateCalibrationDate(dto.getCalibrationDate());
    
    entityManager.flush();
    
    return equipmentMapper.toDto(equipment);
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
