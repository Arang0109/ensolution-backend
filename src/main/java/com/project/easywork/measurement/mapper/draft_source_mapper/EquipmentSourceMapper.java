package com.project.easywork.measurement.mapper.draft_source_mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.equipment.domain.document.spec.GasSamplerSpec;
import com.project.easywork.equipment.domain.document.spec.NozzleSpec;
import com.project.easywork.equipment.domain.document.spec.ParticleSamplerSpec;
import com.project.easywork.equipment.domain.document.spec.PitotTubeSpec;
import com.project.easywork.measurement.domain.dto.draft_source.equipments.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class EquipmentSourceMapper {
  
  private final ObjectMapper objectMapper;
  
  public EquipmentSource toSnapshot(
      EquipmentDoc particleSampler,
      EquipmentDoc gasSampler,
      EquipmentDoc pitotTube,
      EquipmentDoc nozzle
  ) {
    return new EquipmentSource(
        toParticleSnapshot(particleSampler),
        toGasSnapshot(gasSampler),
        toPitotSnapshot(pitotTube),
        toNozzleSnapshot(nozzle)
    );
  }
  
  private ParticleSamplerSource toParticleSnapshot(
      EquipmentDoc doc
  ) {
    
    if (doc == null) return null;
    
    ParticleSamplerSpec spec =
        objectMapper.convertValue(
            doc.getSpec(),
            ParticleSamplerSpec.class
        );
    
    return new ParticleSamplerSource(
        doc.getId(),
        doc.getManagementNumber(),
        doc.getAlias(),
        spec.getTotalVolume(),
        spec.getOrificeDp(),
        spec.getYd()
    );
  }
  
  private GasSamplerSource toGasSnapshot(
      EquipmentDoc doc
  ) {
    
    if (doc == null) return null;
    
    GasSamplerSpec spec =
        objectMapper.convertValue(
            doc.getSpec(),
            GasSamplerSpec.class
        );
    
    return new GasSamplerSource(
        doc.getId(),
        doc.getManagementNumber(),
        doc.getAlias(),
        spec.getTotalVolume()
    );
  }
  
  private PitotTubeSource toPitotSnapshot(
      EquipmentDoc doc
  ) {
    
    if (doc == null) return null;
    
    PitotTubeSpec spec =
        objectMapper.convertValue(
            doc.getSpec(),
            PitotTubeSpec.class
        );
    
    return new PitotTubeSource(
        doc.getId(),
        doc.getManagementNumber(),
        doc.getAlias(),
        spec.getPitotTubeType(),
        toCoefficientSources(spec)
    );
  }
  
  private List<PitotTubeSource.PitotCoefficientSource> toCoefficientSources(PitotTubeSpec spec) {
    
    if (spec.getCoefficients() == null) return List.of();
    
    return spec.getCoefficients().stream()
        .map(c -> new PitotTubeSource.PitotCoefficientSource(
            c.getVelocity(),
            c.getCoefficient()
        ))
        .toList();
  }
  
  private NozzleSource toNozzleSnapshot(
      EquipmentDoc doc
  ) {
    
    if (doc == null) return null;
    
    NozzleSpec spec =
        objectMapper.convertValue(
            doc.getSpec(),
            NozzleSpec.class
        );
    
    return new NozzleSource(
        doc.getId(),
        doc.getManagementNumber(),
        doc.getAlias(),
        toDiameterSources(spec)
    );
  }
  
  private List<NozzleSource.NozzleDiameterSource> toDiameterSources(NozzleSpec spec) {
    
    if (spec.getDiameters() == null) return List.of();
    
    return spec.getDiameters().stream()
        .map(d -> new NozzleSource.NozzleDiameterSource(
            d.getDiameter()
        ))
        .toList();
  }
}