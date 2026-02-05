package com.project.easywork.equipment.service_data.impl;

import com.project.easywork.equipment.domain.EquipType;
import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.equipment.domain.document.spec.*;
import com.project.easywork.equipment.repository.EquipmentRepository;
import com.project.easywork.equipment.service_data.IEquipmentDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EquipmentDataService implements IEquipmentDataService {
  
  private final EquipmentRepository equipmentRepository;
  
  @Override
  public EquipmentDoc save(EquipmentDoc doc) {
    return equipmentRepository.save(doc);
  }
  
  @Override
  public EquipmentDoc findById(String id) {
    return equipmentRepository.findById(id).orElseThrow();
  }
  
  @Override
  public List<EquipmentDoc> findAll() {
    return equipmentRepository.findAll();
  }
  
  @Override
  public List<EquipmentDoc> findByType(EquipType type) {
    Class<?> specClass = getSpecClass(type);
    
    return equipmentRepository.findBySpecType(
        specClass.getName()
    );
  }
  
  @Override
  public void deleteById(String id) {
    equipmentRepository.deleteById(id);
  }
  
  private Class<? extends EquipmentSpec> getSpecClass(EquipType type) {
    return switch (type) {
      case PARTICLE_SAMPLER -> ParticleSamplerSpec.class;
      case GAS_SAMPLER -> GasSamplerSpec.class;
      case PITOT_TUBE -> PitotTubeSpec.class;
      case NOZZLE -> NozzleSpec.class;
      case OTHER -> OtherSpec.class;
    };
  }
}
