package com.project.easywork.plan.mapper;

import com.project.easywork.client.domain.dto.stack.MeasurementHistoryD;
import com.project.easywork.client.mapper.CompanyMapper;
import com.project.easywork.client.mapper.StackMapper;
import com.project.easywork.client.mapper.StackMeasurementMapper;
import com.project.easywork.client.mapper.WorkplaceMapper;
import com.project.easywork.plan.domain.dto.*;
import com.project.easywork.plan.domain.persistance.Plan;
import com.project.easywork.plan.domain.persistance.PlanMeasurement;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder(),
    uses = {
        PlanMeasurementMapper.class,
        StackMeasurementMapper.class,
        StackMapper.class,
        WorkplaceMapper.class,
        CompanyMapper.class
    }
)
public interface PlanMapper {
  Plan toEntity(PlanCreateD dto);
  
  @Mapping(target = "stackId", source = "stack.id")
  @Mapping(target = "teamId", source = "team.id")
  PlanD toDto(Plan plan);
  
  @Mapping(source = ".", target = "plan")
  @Mapping(source = "stack", target = "stack")
  @Mapping(source = "stack.workplace", target = "workplace")
  @Mapping(source = "stack.workplace.company", target = "company")
  PlanDetailD toDetailDto(Plan plan);
  
  @Mapping(source = "id", target = "planId")
  @Mapping(source = "measureDate", target = "measureDate")
  @Mapping(source = "team.name", target = "teamName")
  @Mapping(target = "measurements", source = "measurements", qualifiedByName = "toPollutantNames")
  MeasurementHistoryD toMeasurementHistoryDto(Plan plan);
  
  @Mapping(target = "stackName", source = "stack.name")
  @Mapping(target = "workplaceName", source = "stack.workplace.name")
  @Mapping(target = "companyName", source = "stack.workplace.company.name")
  @Mapping(target = "teamName", source = "team.name")
  @Mapping(target = "measurements", source = "measurements", qualifiedByName = "toPollutantNames")
  PlanTableViewD toTable(Plan plan);
  
  List<PlanD> toDtoList(List<Plan> plans);
  List<PlanTableViewD> toTableList(List<Plan> plans);
  List<MeasurementHistoryD> toMeasurementHistory(List<Plan> plans);
  
  @Named("toPollutantNames")
  default List<String> toPollutantNames(List<PlanMeasurement> measurements) {
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
