package com.project.easywork.schedule.mapper;

import com.project.easywork.client.mapper.StackMeasurementMapper;
import com.project.easywork.schedule.domain.dto.ScheduleMeasurementCreateReqDto;
import com.project.easywork.schedule.domain.dto.ScheduleMeasurementResDto;
import com.project.easywork.schedule.domain.persistance.ScheduleMeasurement;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder(),
    uses = {
        StackMeasurementMapper.class
    }
)
public interface ScheduleMeasurementMapper {
  
  @Mapping(source = "stackMeasurementId", target = "stackMeasurement.id")
  @Mapping(source = "scheduleId", target = "schedule.id")
  ScheduleMeasurement toEntity(ScheduleMeasurementCreateReqDto dto);
  
  @Mapping(target = "scheduleId", source = "schedule.id")
  @Mapping(target = "stackMeasurement", source = "stackMeasurement")
  ScheduleMeasurementResDto toDto(ScheduleMeasurement scheduleMeasurement);
  
  List<ScheduleMeasurementResDto> toDtoList(List<ScheduleMeasurement> scheduleMeasurements);
  
  List<ScheduleMeasurement> toEntityList(
      List<ScheduleMeasurementCreateReqDto> dtos
  );
}