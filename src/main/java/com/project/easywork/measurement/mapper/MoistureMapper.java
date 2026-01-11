package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.command.MoistureCommandD;
import com.project.easywork.measurement.dto.document.input.MoistureDoc;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MoistureMapper {
  MoistureDoc toDocument(MoistureCommandD dto);
  MoistureDoc.WeightDocument toDocument(MoistureCommandD.Weight dto);
  MoistureDoc.GasMeterTemperatureDocument toDocument(MoistureCommandD.GasMeterTemperature dto);
  MoistureDoc.DryGasVolumeDocument toDocument(MoistureCommandD.DryGasVolume dto);
}

