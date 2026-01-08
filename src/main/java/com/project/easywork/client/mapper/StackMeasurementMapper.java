package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.stack.MeasurementListD;
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
  
  @Mapping(source = "stack.id", target = "stackId")
  StackMeasurementD toDto(StackMeasurement stackMeasurement);
  
  @Mapping(source = "pollutant.id", target = "pollutantId")
  @Mapping(source = "pollutant.nameKr", target = "nameKr")
  @Mapping(source = "pollutant.nameEn", target = "nameEn")
  MeasurementListD toMeasurementDto(StackMeasurement stackMeasurement);
  
  List<StackMeasurementD> toDtoList(List<StackMeasurement> stackMeasurements);
  List<MeasurementListD> toMeasurementDtoList(List<StackMeasurement> stackMeasurements);
}