package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.command.WeatherCommandDto;
import com.project.easywork.measurement.dto.document.input.WeatherDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherMapper {
  WeatherDocument toDocument(WeatherCommandDto dto);
  
  WeatherDocument.WeatherPressureDocument toDocument(WeatherCommandDto.WeatherPressure pressure);
}

