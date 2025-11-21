package com.project.easywork.client.mapper;

import com.project.easywork.client.dto.PreventionDto;
import com.project.easywork.client.dto.view.PreventionDetailDto;
import com.project.easywork.client.entity.Prevention;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PreventionMapper {
  @Mapping(target = "stack.stackId", source = "stackId")
  Prevention toEntity(PreventionDto dto);
  
  @Mapping(target = "stackId", source = "stack.stackId")
  PreventionDto toDto(Prevention entity);
  
  List<PreventionDto> toDtoList(List<Prevention> preventions);
  
  @Mapping(target = "facilities", source = "facilities")
  @Mapping(target = "targets", source = "targets")
  PreventionDetailDto toDetailDto(Prevention prevention);
}