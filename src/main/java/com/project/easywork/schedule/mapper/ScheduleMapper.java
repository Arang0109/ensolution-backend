package com.project.easywork.schedule.mapper;

import com.project.easywork.schedule.domain.dto.ScheduleCreateRequestDto;
import com.project.easywork.schedule.domain.dto.ScheduleResponseDto;
import com.project.easywork.schedule.domain.persistance.Schedule;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder())
public interface ScheduleMapper {
  @Mapping(target = "stack.id", source = "stackId")
  @Mapping(target = "team.id", source = "teamId")
  Schedule toEntityFromScheduleCreateDto(ScheduleCreateRequestDto dto);
  
  @Mapping(target = "stackId", source = "stack.id")
  @Mapping(target = "teamId", source = "team.id")
  ScheduleResponseDto toDto(Schedule schedule);
  
  List<ScheduleResponseDto> toDtoList(List<Schedule> schedules);
}
