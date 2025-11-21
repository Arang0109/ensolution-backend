package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.command.MoistureCommandDto;
import com.project.easywork.measurement.dto.document.input.MoistureDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MoistureMapper {
  MoistureDocument toDocument(MoistureCommandDto dto);
  MoistureDocument.WeightDocument toDocument(MoistureCommandDto.Weight dto);
  MoistureDocument.GasMeterTemperatureDocument toDocument(MoistureCommandDto.GasMeterTemperature dto);
  MoistureDocument.DryGasVolumeDocument toDocument(MoistureCommandDto.DryGasVolume dto);
}

