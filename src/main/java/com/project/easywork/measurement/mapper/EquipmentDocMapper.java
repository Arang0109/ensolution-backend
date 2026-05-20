package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.domain.document.equipments.EquipmentSnapshotDoc;
import com.project.easywork.measurement.domain.document.equipments.spec.NozzleSnapshotDoc;
import com.project.easywork.measurement.domain.document.equipments.spec.PitotTubeSnapshotDoc;
import com.project.easywork.measurement.domain.dto.draft_source.equipments.EquipmentSource;
import com.project.easywork.measurement.domain.dto.draft_source.equipments.NozzleSource;
import com.project.easywork.measurement.domain.dto.draft_source.equipments.PitotTubeSource;
import com.project.easywork.report.domain.equipment.EquipmentDataD;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EquipmentDocMapper {
  
  @Mapping(target = "deltaH", source = "particleSampler.deltaH")
  @Mapping(target = "yd", source = "particleSampler.yd")
  EquipmentDataD toEquipmentDataDto(EquipmentSnapshotDoc doc);
  
  EquipmentSnapshotDoc toDoc(EquipmentSource snapshot);
  
  PitotTubeSnapshotDoc toDoc(PitotTubeSource snapshot);
  
  PitotTubeSnapshotDoc.PitotCoefficient toDoc(
      PitotTubeSource.PitotCoefficientSource snapshot
  );
  
  NozzleSnapshotDoc toDoc(NozzleSource snapshot);
  
  NozzleSnapshotDoc.NozzleDiameter toDoc(
      NozzleSource.NozzleDiameterSource snapshot
  );
}
