package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.target.TargetCreateRequestDto;
import com.project.easywork.client.domain.dto.target.TargetResponseDto;
import com.project.easywork.client.domain.dto.target.TargetUpdateRequestDto;
import com.project.easywork.client.domain.persistance.Target;
import org.mapstruct.*;

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
  
  @BeanMapping(
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
  )
  void updateTarget(TargetUpdateRequestDto dto, @MappingTarget Target target);
}