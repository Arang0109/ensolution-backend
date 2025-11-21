package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.command.MeasurementCommandDto;
import com.project.easywork.measurement.dto.document.*;
import com.project.easywork.measurement.dto.document.input.ExhaustGasDocument;
import com.project.easywork.measurement.dto.document.input.MoistureDocument;
import com.project.easywork.measurement.dto.document.input.PreInfoDocument;
import com.project.easywork.measurement.dto.document.input.WeatherDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MeasurementMapper {
  
  // MeasurementCommandDto → MeasurementDocument
  @Mapping(target = "id", ignore = true) // MongoDB에서 자동 생성
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  MeasurementDocument toDocument(MeasurementCommandDto dto);
  
  // Nested mappings
  PreInfoDocument toDocument(com.project.easywork.measurement.dto.command.PreInfoCommandDto dto);
  
  WeatherDocument toDocument(com.project.easywork.measurement.dto.command.WeatherCommandDto dto);
  
  WeatherDocument.WeatherPressureDocument toDocument(
      com.project.easywork.measurement.dto.command.WeatherCommandDto.WeatherPressure dto
  );
  
  MoistureDocument toDocument(com.project.easywork.measurement.dto.command.MoistureCommandDto dto);
  
  MoistureDocument.WeightDocument toDocument(
      com.project.easywork.measurement.dto.command.MoistureCommandDto.Weight dto
  );
  
  MoistureDocument.GasMeterTemperatureDocument toDocument(
      com.project.easywork.measurement.dto.command.MoistureCommandDto.GasMeterTemperature dto
  );
  
  MoistureDocument.DryGasVolumeDocument toDocument(
      com.project.easywork.measurement.dto.command.MoistureCommandDto.DryGasVolume dto
  );
  
  ExhaustGasDocument toDocument(com.project.easywork.measurement.dto.command.ExhaustGasCommandDto dto);
  
  ExhaustGasDocument.DynamicPressureDocument toDocument(
      com.project.easywork.measurement.dto.command.ExhaustGasCommandDto.DynamicPressure dto
  );
  
  ExhaustGasDocument.StaticPressureDocument toDocument(
      com.project.easywork.measurement.dto.command.ExhaustGasCommandDto.StaticPressure dto
  );
}