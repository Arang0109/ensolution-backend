package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementCreateRequestDto;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementResponseDto;
import com.project.easywork.client.domain.persistance.StackMeasurement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StackMeasurementMapper {
  
  @Mapping(target = "stack.id", source = "stackId")
  @Mapping(target = "pollutant.id", source = "pollutantId")
  StackMeasurement toEntityFromStackMeasurementCreateDto(StackMeasurementCreateRequestDto dto);
  
  @Mapping(target = "stackId", source = "stack.id")
  @Mapping(target = "pollutantId", source = "pollutant.id")
  StackMeasurementResponseDto toDto(StackMeasurement stackMeasurement);
  
  List<StackMeasurementResponseDto> toDtoList(List<StackMeasurement> stackMeasurements);
}