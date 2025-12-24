package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.prevention.PreventionCreateRequestDto;
import com.project.easywork.client.domain.dto.prevention.PreventionDetailResponseDto;
import com.project.easywork.client.domain.dto.prevention.PreventionResponseDto;
import com.project.easywork.client.domain.dto.prevention.PreventionUpdateRequestDto;
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
  Prevention toEntity(PreventionCreateRequestDto dto);
  
  @Mapping(source = "stack.id", target = "stackId")
  PreventionResponseDto toDto(Prevention prevention);
  
  @Mapping(source = ".", target = "prevention")
  PreventionDetailResponseDto toDetailDto(Prevention prevention);
  
  List<PreventionResponseDto> toDtoList(List<Prevention> preventions);
  
  @BeanMapping(
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
  )
  void updatePrevention(PreventionUpdateRequestDto dto, @MappingTarget Prevention prevention);
}