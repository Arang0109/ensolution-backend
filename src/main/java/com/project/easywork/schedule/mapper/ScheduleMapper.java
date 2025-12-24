package com.project.easywork.schedule.mapper;

import com.project.easywork.agency.mapper.TeamMapper;
import com.project.easywork.client.mapper.StackMapper;
import com.project.easywork.schedule.domain.dto.ScheduleCreateReqDto;
import com.project.easywork.schedule.domain.dto.ScheduleResDto;
import com.project.easywork.schedule.domain.dto.ScheduleTableViewDto;
import com.project.easywork.schedule.domain.persistance.Schedule;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder()
)
public interface ScheduleMapper {
  Schedule toEntity(ScheduleCreateReqDto dto);
  
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
