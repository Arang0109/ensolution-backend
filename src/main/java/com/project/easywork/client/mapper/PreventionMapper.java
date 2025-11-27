package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.prevention.PreventionDto;
import com.project.easywork.client.domain.dto.prevention.PreventionDetailDto;
import com.project.easywork.client.domain.persistance.Prevention;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PreventionMapper {
  @Mapping(target = "stack.id", source = "stackId")
  Prevention toEntity(PreventionDto dto);
  
  @Mapping(target = "stackId", source = "stack.id")
  PreventionDto toDto(Prevention entity);
  
  List<PreventionDto> toDtoList(List<Prevention> preventions);
  
  @Mapping(target = "facilities", source = "facilities")
  @Mapping(target = "targets", source = "targets")
  PreventionDetailDto toDetailDto(Prevention prevention);
}