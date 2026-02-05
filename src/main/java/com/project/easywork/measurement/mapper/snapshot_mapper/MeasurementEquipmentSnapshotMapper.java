package com.project.easywork.measurement.mapper.snapshot_mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.equipment.domain.document.spec.GasSamplerSpec;
import com.project.easywork.equipment.domain.document.spec.NozzleSpec;
import com.project.easywork.equipment.domain.document.spec.ParticleSamplerSpec;
import com.project.easywork.equipment.domain.document.spec.PitotTubeSpec;
import com.project.easywork.measurement.dto.snapshot.equipment.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class MeasurementEquipmentSnapshotMapper {
  
  private final ObjectMapper objectMapper;
  
  public MeasurementEquipmentSnapshot toSnapshot(
      EquipmentDoc particleSampler,
      EquipmentDoc gasSampler,
      EquipmentDoc pitotTube,
      EquipmentDoc nozzle
  ) {
    return new MeasurementEquipmentSnapshot(
        toParticleSnapshot(particleSampler),
        toGasSnapshot(gasSampler),
        toPitotSnapshot(pitotTube),
        toNozzleSnapshot(nozzle)
    );
  }
  
  private ParticleSamplerSnapshot toParticleSnapshot(
      EquipmentDoc doc
  ) {
    
    if (doc == null) return null;
    
    ParticleSamplerSpec spec =
        objectMapper.convertValue(
            doc.getSpec(),
            ParticleSamplerSpec.class
        );
    
    return new ParticleSamplerSnapshot(
        doc.getId(),
        doc.getManagementNumber(),
        doc.getAlias(),
        spec.getTotalVolume(),
        spec.getOrificeDp(),
        spec.getYd()
    );
  }
  
  private GasSamplerSnapshot toGasSnapshot(
      EquipmentDoc doc
  ) {
    
    if (doc == null) return null;
    
    GasSamplerSpec spec =
        objectMapper.convertValue(
            doc.getSpec(),
            GasSamplerSpec.class
        );
    
    return new GasSamplerSnapshot(
        doc.getId(),
        doc.getManagementNumber(),
        doc.getAlias(),
        spec.getTotalVolume()
    );
  }
  
  private PitotTubeSnapshot toPitotSnapshot(
      EquipmentDoc doc
  ) {
    
    if (doc == null) return null;
    
    PitotTubeSpec spec =
        objectMapper.convertValue(
            doc.getSpec(),
            PitotTubeSpec.class
        );
    
    return new PitotTubeSnapshot(
        doc.getId(),
        doc.getManagementNumber(),
        doc.getAlias(),
        spec.getPitotTubeType(),
        toCoefficientSnapshots(spec)
    );
  }
  
  private List<PitotTubeSnapshot.PitotCoefficientSnapshot> toCoefficientSnapshots(PitotTubeSpec spec) {
    
    if (spec.getCoefficients() == null) return List.of();
    
    return spec.getCoefficients().stream()
        .map(c -> new PitotTubeSnapshot.PitotCoefficientSnapshot(
            c.getVelocity(),
            c.getCoefficient()
        ))
        .toList();
  }
  
  private NozzleSnapshot toNozzleSnapshot(
      EquipmentDoc doc
  ) {
    
    if (doc == null) return null;
    
    NozzleSpec spec =
        objectMapper.convertValue(
            doc.getSpec(),
            NozzleSpec.class
        );
    
    return new NozzleSnapshot(
        doc.getId(),
        doc.getManagementNumber(),
        doc.getAlias(),
        toDiameterSnapshots(spec)
    );
  }
  
  private List<NozzleSnapshot.NozzleDiameterSnapshot> toDiameterSnapshots(NozzleSpec spec) {
    
    if (spec.getDiameters() == null) return List.of();
    
    return spec.getDiameters().stream()
        .map(d -> new NozzleSnapshot.NozzleDiameterSnapshot(
            d.getDiameter()
        ))
        .toList();
  }
}