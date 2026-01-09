package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.prevention.PreventionCreateD;
import com.project.easywork.client.domain.dto.prevention.PreventionDetailD;
import com.project.easywork.client.domain.dto.prevention.PreventionD;
import com.project.easywork.client.domain.persistance.Prevention;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder(),
    uses = {
        FacilityMapper.class,
        TargetMapper.class
    }
)
public interface PreventionMapper {
  Prevention toEntity(PreventionCreateD dto);
  
  @Mapping(source = "stack.id", target = "stackId")
  PreventionD toDto(Prevention prevention);
  
  @Mapping(source = ".", target = "prevention")
  PreventionDetailD toDetailDto(Prevention prevention);
  
  List<PreventionD> toDtoList(List<Prevention> preventions);
}