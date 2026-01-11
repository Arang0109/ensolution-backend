package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.command.WeatherCommandD;
import com.project.easywork.measurement.dto.document.input.WeatherDoc;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherMapper {
  WeatherDoc toDocument(WeatherCommandD dto);
  
  WeatherDoc.WeatherPressureDocument toDocument(WeatherCommandD.WeatherPressure pressure);
}

