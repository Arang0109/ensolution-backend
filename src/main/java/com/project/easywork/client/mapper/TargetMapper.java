package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.target.TargetCreateRequestDto;
import com.project.easywork.client.domain.dto.target.TargetResponseDto;
import com.project.easywork.client.domain.persistance.Target;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TargetMapper {
  @Mapping(target = "prevention.id", source = "preventionId")
  Target toEntityFromFacilityCreateDto(TargetCreateRequestDto dto);
  
  @Mapping(target = "preventionId", source = "prevention.id")
  TargetResponseDto toDto(Target target);
  
  List<TargetResponseDto> toDtoList(List<Target> targets);
}