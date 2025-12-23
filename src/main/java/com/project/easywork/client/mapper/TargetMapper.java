package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.target.TargetCreateRequestDto;
import com.project.easywork.client.domain.dto.target.TargetResponseDto;
import com.project.easywork.client.domain.dto.target.TargetUpdateRequestDto;
import com.project.easywork.client.domain.persistance.Target;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder()
)
public interface TargetMapper {
  Target toEntity(TargetCreateRequestDto dto);
  
  @Mapping(target = "preventionId", source = "prevention.id")
  TargetResponseDto toDto(Target target);
  
  List<TargetResponseDto> toDtoList(List<Target> targets);
  List<Target> toEntityList(List<TargetCreateRequestDto> targets);
  
  void updateTarget(TargetUpdateRequestDto dto, @MappingTarget Target target);
}