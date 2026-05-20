package com.project.easywork.plan.mapper;

import com.project.easywork.client.mapper.CompanyMapper;
import com.project.easywork.client.mapper.StackMapper;
import com.project.easywork.client.mapper.WorkplaceMapper;
import com.project.easywork.plan.domain.dto.PlanCreateD;
import com.project.easywork.plan.domain.dto.PlanD;
import com.project.easywork.plan.domain.persistance.Plan;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder(),
    uses = {
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
  
  List<PlanD> toDtoList(List<Plan> plans);
}
