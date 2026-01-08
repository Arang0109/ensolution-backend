package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementCreateD;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementD;
import com.project.easywork.client.domain.persistance.StackMeasurement;
import com.project.easywork.pollutant.mapper.PollutantMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder(),
    uses = {
        PollutantMapper.class
    }
)
public interface StackMeasurementMapper {
  StackMeasurement toEntity(StackMeasurementCreateD dto);
  
  @Mapping(target = "stackId", source = "stack.id")
  StackMeasurementD toDto(StackMeasurement stackMeasurement);
  
  List<StackMeasurementD> toDtoList(List<StackMeasurement> stackMeasurements);
}