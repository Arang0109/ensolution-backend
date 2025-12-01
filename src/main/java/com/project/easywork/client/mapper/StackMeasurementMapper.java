package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementCreateRequestDto;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementResponseDto;
import com.project.easywork.client.domain.persistance.StackMeasurement;
import com.project.easywork.pollutant.mapper.PollutantMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { PollutantMapper.class })
public interface StackMeasurementMapper {
  
  @Mapping(target = "stack.id", source = "stackId")
  @Mapping(target = "pollutant.id", source = "pollutantId")
  StackMeasurement toEntityFromStackMeasurementCreateDto(StackMeasurementCreateRequestDto dto);
  
  @Mapping(target = "stackId", source = "stack.id")
  @Mapping(target = "pollutant", source = "pollutant")
  StackMeasurementResponseDto toDto(StackMeasurement stackMeasurement);
  
  List<StackMeasurementResponseDto> toDtoList(List<StackMeasurement> stackMeasurements);
}