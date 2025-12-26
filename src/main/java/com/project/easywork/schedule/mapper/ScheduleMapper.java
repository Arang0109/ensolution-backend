package com.project.easywork.schedule.mapper;

import com.project.easywork.agency.mapper.TeamMapper;
import com.project.easywork.client.domain.persistance.StackMeasurement;
import com.project.easywork.client.mapper.StackMapper;
import com.project.easywork.client.mapper.StackMeasurementMapper;
import com.project.easywork.schedule.domain.dto.ScheduleCreateReqDto;
import com.project.easywork.schedule.domain.dto.ScheduleDetailResDto;
import com.project.easywork.schedule.domain.dto.ScheduleResDto;
import com.project.easywork.schedule.domain.dto.ScheduleTableViewDto;
import com.project.easywork.schedule.domain.persistance.Schedule;
import com.project.easywork.schedule.domain.persistance.ScheduleMeasurement;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder(),
    uses = {
        StackMeasurementMapper.class
    }
)
public interface ScheduleMapper {
  Schedule toEntity(ScheduleCreateReqDto dto);
  
  @Mapping(target = "stackId", source = "stack.id")
  @Mapping(target = "teamId", source = "team.id")
  ScheduleResDto toDto(Schedule schedule);
  
  @Mapping(source = ".", target = "schedule")
  ScheduleDetailResDto toDetailDto(Schedule schedule);
  
  @Mapping(target = "stackName", source = "stack.name")
  @Mapping(target = "workplaceName", source = "stack.workplace.name")
  @Mapping(target = "companyName", source = "stack.workplace.company.name")
  @Mapping(target = "teamName", source = "team.name")
  @Mapping(target = "measurements", source = "measurements", qualifiedByName = "toPollutantNames")
  ScheduleTableViewDto toTable(Schedule schedule);
  
  List<ScheduleResDto> toDtoList(List<Schedule> schedules);
  List<ScheduleTableViewDto> toTableList(List<Schedule> schedules);
  
  @Named("toPollutantNames")
  default List<String> toPollutantNames(List<ScheduleMeasurement> measurements) {
    if (measurements == null) return List.of();
    
    return measurements.stream()
        .map(m -> {
          if (m.getStackMeasurement() == null) return null;
          
          String kr = m.getStackMeasurement().getPollutant().getNameKr();
          String en = m.getStackMeasurement().getPollutant().getNameEn();
          return kr != null ? kr : en;
        })
        .toList();
  }
}
