package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.command.EquipmentCommandD;
import com.project.easywork.measurement.dto.document.input.MeasurementEquipmentDoc;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipmentDocMapper {
  MeasurementEquipmentDoc toDocument(EquipmentCommandD dto);
  MeasurementEquipmentDoc.ParticleSamplerSnapshot toDocument(
      EquipmentCommandD.ParticularEquipment particularEquipment
  );
}
