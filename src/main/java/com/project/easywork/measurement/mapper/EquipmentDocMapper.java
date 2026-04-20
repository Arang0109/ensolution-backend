package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.document.input.MeasurementEquipmentDoc;
import com.project.easywork.measurement.dto.snapshot.equipment.MeasurementEquipmentSnapshot;
import com.project.easywork.measurement.dto.snapshot.equipment.NozzleSnapshot;
import com.project.easywork.measurement.dto.snapshot.equipment.PitotTubeSnapshot;
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
  EquipmentDataD toEquipmentDataDto(MeasurementEquipmentDoc doc);
  
  MeasurementEquipmentDoc toDoc(MeasurementEquipmentSnapshot snapshot);
  
  MeasurementEquipmentDoc.PitotTubeDoc toDoc(PitotTubeSnapshot snapshot);
  
  MeasurementEquipmentDoc.PitotTubeDoc.PitotCoefficient toDoc(
      PitotTubeSnapshot.PitotCoefficientSnapshot snapshot
  );
  
  MeasurementEquipmentDoc.NozzleDoc toDoc(NozzleSnapshot snapshot);
  
  MeasurementEquipmentDoc.NozzleDoc.NozzleDiameter toDoc(
      NozzleSnapshot.NozzleDiameterSnapshot snapshot
  );
}
