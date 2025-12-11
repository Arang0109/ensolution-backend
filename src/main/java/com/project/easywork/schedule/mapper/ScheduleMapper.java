package com.project.easywork.schedule.mapper;

import com.project.easywork.schedule.domain.dto.ScheduleCreateReqDto;
import com.project.easywork.schedule.domain.dto.ScheduleResDto;
import com.project.easywork.schedule.domain.dto.ScheduleTableViewDto;
import com.project.easywork.schedule.domain.persistance.Schedule;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder(),
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ScheduleMapper {
  @Mapping(target = "stack.id", source = "stackId")
  @Mapping(target = "team.id", source = "teamId")
  Schedule toEntityFromScheduleCreateDto(ScheduleCreateReqDto dto);
  
  @Mapping(target = "stackId", source = "stack.id")
  @Mapping(target = "teamId", source = "team.id")
  ScheduleResDto toDto(Schedule schedule);
  
  @Mapping(target = "stackId", source = "stack.id")
  @Mapping(target = "stackName", source = "stack.name")
  @Mapping(target = "teamId", source = "team.id")
  @Mapping(target = "teamName", source = "team.name")
  @Mapping(target = "workplaceId", source = "stack.workplace.id")
  @Mapping(target = "workplaceName", source = "stack.workplace.name")
  ScheduleTableViewDto toTable(Schedule schedule);
  
  List<ScheduleResDto> toDtoList(List<Schedule> schedules);
  List<ScheduleTableViewDto> toTableList(List<Schedule> schedules);
}
