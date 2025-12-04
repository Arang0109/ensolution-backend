package com.project.easywork.schedule.mapper;

import com.project.easywork.schedule.domain.dto.ScheduleCreateRequestDto;
import com.project.easywork.schedule.domain.dto.ScheduleResponseDto;
import com.project.easywork.schedule.domain.persistance.Schedule;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder())
public interface ScheduleMapper {
  Schedule toEntityFromScheduleCreateDto(ScheduleCreateRequestDto dto);
  ScheduleResponseDto toDto(Schedule schedule);
  
  List<ScheduleResponseDto> toDtoList(List<Schedule> schedules);
}
