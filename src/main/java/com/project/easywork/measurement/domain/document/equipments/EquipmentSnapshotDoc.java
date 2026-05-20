package com.project.easywork.measurement.domain.document.equipments;

import com.project.easywork.measurement.domain.document.equipments.spec.GasSamplerSnapshotDoc;
import com.project.easywork.measurement.domain.document.equipments.spec.NozzleSnapshotDoc;
import com.project.easywork.measurement.domain.document.equipments.spec.ParticleSamplerSnapshotDoc;
import com.project.easywork.measurement.domain.document.equipments.spec.PitotTubeSnapshotDoc;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentSnapshotDoc {
  
  private ParticleSamplerSnapshotDoc particleSampler;
  private GasSamplerSnapshotDoc gasSampler;
  private PitotTubeSnapshotDoc pitotTube;
  private NozzleSnapshotDoc nozzle;
  
  public EquipmentSnapshotDoc merge(EquipmentSnapshotDoc patch) {
    return this.toBuilder()
      .particleSampler(
        patch.getParticleSampler() != null
          ? patch.getParticleSampler()
          : this.particleSampler
      )
      .gasSampler(
        patch.getGasSampler() != null
          ? patch.getGasSampler()
          : this.gasSampler
      )
      .pitotTube(
        patch.getPitotTube() != null
          ? patch.getPitotTube()
          : this.pitotTube
      )
      .nozzle(
        patch.getNozzle() != null
          ? patch.getNozzle()
          : this.nozzle
      )
      .build();
  }
}
