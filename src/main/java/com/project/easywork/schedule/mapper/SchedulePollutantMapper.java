package com.project.easywork.schedule.mapper;

import com.project.easywork.schedule.domain.dto.SchedulePollutantCreateReqDto;
import com.project.easywork.schedule.domain.dto.SchedulePollutantResDto;
import com.project.easywork.schedule.domain.persistance.SchedulePollutant;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder(),
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SchedulePollutantMapper {
  @Mapping(target = "schedule.id", source = "scheduleId")
  @Mapping(target = "stackMeasurement.id", source = "stackMeasurementId")
  SchedulePollutant toEntity(SchedulePollutantCreateReqDto dto);
  
  @Mapping(target = "scheduleId", source = "schedule.id")
  @Mapping(target = "stackMeasurement", source = "stackMeasurement")
  SchedulePollutantResDto toDto(SchedulePollutant schedulePollutant);
  
  List<SchedulePollutantResDto> toDtoList(List<SchedulePollutant> schedulePollutants);
  List<SchedulePollutant> toEntityList(List<SchedulePollutantCreateReqDto> schedulePollutantCreateReqDtos);
}
