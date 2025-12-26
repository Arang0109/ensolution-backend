package com.project.easywork.schedule.mapper;

import com.project.easywork.client.mapper.CompanyMapper;
import com.project.easywork.client.mapper.StackMapper;
import com.project.easywork.client.mapper.StackMeasurementMapper;
import com.project.easywork.client.mapper.WorkplaceMapper;
import com.project.easywork.schedule.domain.dto.*;
import com.project.easywork.schedule.domain.persistance.Schedule;
import com.project.easywork.schedule.domain.persistance.ScheduleMeasurement;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder(),
    uses = {
        StackMeasurementMapper.class,
        StackMapper.class,
        WorkplaceMapper.class,
        CompanyMapper.class
    }
)
public interface ScheduleMapper {
  Schedule toEntity(ScheduleCreateReqDto dto);
  
  @Mapping(target = "stackId", source = "stack.id")
  @Mapping(target = "teamId", source = "team.id")
  ScheduleResDto toDto(Schedule schedule);
  
  @Mapping(source = ".", target = "schedule")
  @Mapping(source = "stack", target = "stack")
  @Mapping(source = "stack.workplace", target = "workplace")
  @Mapping(source = "stack.workplace.company", target = "company")
  ScheduleDetailResDto toDetailDto(Schedule schedule);
  
  @Mapping(target = "stackName", source = "stack.name")
  @Mapping(target = "workplaceName", source = "stack.workplace.name")
  @Mapping(target = "companyName", source = "stack.workplace.company.name")
  @Mapping(target = "teamName", source = "team.name")
  @Mapping(target = "measurements", source = "measurements", qualifiedByName = "toPollutantNames")
  ScheduleTableViewDto toTable(Schedule schedule);
  
  List<ScheduleResDto> toDtoList(List<Schedule> schedules);
  List<ScheduleTableViewDto> toTableList(List<Schedule> schedules);
  
  @BeanMapping(
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
  )
  @Mapping(target = "stack", ignore = true)
  @Mapping(target = "team", ignore = true)
  void updateSchedule(ScheduleUpdateReqDto dto, @MappingTarget Schedule schedule);
  
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
