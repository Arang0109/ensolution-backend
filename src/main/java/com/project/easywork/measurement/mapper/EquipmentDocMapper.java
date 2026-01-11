package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.command.EquipmentCommandD;
import com.project.easywork.measurement.dto.document.input.EquipmentDoc;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipmentDocMapper {
  EquipmentDoc toDocument(EquipmentCommandD dto);
  EquipmentDoc.ParticularEquipmentDoc toDocument(EquipmentCommandD.ParticularEquipment particularEquipment);
  EquipmentDoc.PitotTubeDoc toDocument(EquipmentCommandD.PitotTube pitotTube);
  EquipmentDoc.PitotTubeDoc.CoefficientDoc toDocument(
      EquipmentCommandD.PitotTube.Coefficient coefficient
  );
}
