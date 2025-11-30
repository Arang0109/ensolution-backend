package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.prevention.PreventionCreateRequestDto;
import com.project.easywork.client.domain.dto.prevention.PreventionResponseDto;
import com.project.easywork.client.domain.persistance.Prevention;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PreventionMapper {
  @Mapping(target = "stack.id", source = "stackId")
  Prevention toEntityFromPreventionCreateDto(PreventionCreateRequestDto dto);
  
  @Mapping(target = "stackId", source = "stack.id")
  PreventionResponseDto toDto(Prevention prevention);
  
  List<PreventionResponseDto> toDtoList(List<Prevention> preventions);
}