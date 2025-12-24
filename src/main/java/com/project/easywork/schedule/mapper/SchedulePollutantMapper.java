package com.project.easywork.schedule.mapper;

import com.project.easywork.client.mapper.StackMeasurementMapper;
import com.project.easywork.schedule.domain.dto.SchedulePollutantCreateReqDto;
import com.project.easywork.schedule.domain.dto.SchedulePollutantResDto;
import com.project.easywork.schedule.domain.persistance.SchedulePollutant;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder(),
    uses = {
        StackMeasurementMapper.class
    }
)
public interface SchedulePollutantMapper {
  SchedulePollutant toEntity(SchedulePollutantCreateReqDto dto);
  
  @Mapping(target = "scheduleId", source = "schedule.id")
  @Mapping(target = "stackMeasurement", source = "stackMeasurement")
  SchedulePollutantResDto toDto(SchedulePollutant schedulePollutant);
  
  List<SchedulePollutantResDto> toDtoList(List<SchedulePollutant> schedulePollutants);
  List<SchedulePollutant> toEntityList(List<SchedulePollutantCreateReqDto> schedulePollutantCreateReqDtos);
}