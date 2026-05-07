package com.project.easywork.measurement.service.impl.assembler;

import com.project.easywork.measurement.domain.dto.draft_source.DraftSource;
import com.project.easywork.measurement.domain.dto.draft_source.DraftSourceMaterial;
import com.project.easywork.measurement.mapper.draft_source_mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DraftSourceAssembler {
  
  private final BasicInfoSourceMapper basicInfoSourceMapper;
  private final TeamSourceMapper teamSourceMapper;
  private final ClientSourceMapper clientMapper;
  private final EquipmentSourceMapper equipmentMapper;
  private final MeasurementItemSourceMapper measurementItemMapper;
  
  public DraftSource assemble(DraftSourceMaterial m) {
    
    return new DraftSource(
        basicInfoSourceMapper.toSource(m.referenceNumber(), m.measureDate(), m.measurementField(), m.measurementType()),
        teamSourceMapper.toSource(m.team().getId(), m.team().getName(), m.vehicleNumber(), m.mentor(), m.mentee()),
        clientMapper.toSource(m.stack()),
        equipmentMapper.toSnapshot(m.particleSampler(), m.gasSampler(), m.pitotTube(), m.nozzle()),
        measurementItemMapper.toSources(m.measurementItems())
    );
  }
}