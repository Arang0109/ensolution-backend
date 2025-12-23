package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementCreateRequestDto;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementResponseDto;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementUpdateRequestDto;
import com.project.easywork.client.domain.persistance.StackMeasurement;
import com.project.easywork.pollutant.mapper.PollutantMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(
    componentModel = "spring",
    uses = {
        PollutantMapper.class
    }
)
public interface StackMeasurementMapper {
  StackMeasurement toEntity(StackMeasurementCreateRequestDto dto);
  
  @Mapping(target = "stackId", source = "stack.id")
  StackMeasurementResponseDto toDto(StackMeasurement stackMeasurement);
  
  List<StackMeasurementResponseDto> toDtoList(List<StackMeasurement> stackMeasurements);
  
  void updateStackMeasurement(
      StackMeasurementUpdateRequestDto dto, @MappingTarget StackMeasurement stackMeasurement
  );
}