package com.project.easywork.equipment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.easywork.equipment.domain.EquipType;
import com.project.easywork.equipment.domain.document.spec.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpecFactory {
  
  private final ObjectMapper objectMapper;

  public EquipmentSpec toSpec(Object dto, EquipType type) {
    return switch (type) {
      case PARTICLE_SAMPLER -> objectMapper.convertValue(dto, ParticleSamplerSpec.class);
      
      case GAS_SAMPLER -> objectMapper.convertValue(dto, GasSamplerSpec.class);
      
      case PITOT_TUBE -> objectMapper.convertValue(dto, PitotTubeSpec.class);
      
      case NOZZLE -> objectMapper.convertValue(dto, NozzleSpec.class);
      
      case OTHER -> objectMapper.convertValue(dto, OtherSpec.class);
    };
  }
}
