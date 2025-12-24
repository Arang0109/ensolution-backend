package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.stack.StackCreateRequestDto;
import com.project.easywork.client.domain.dto.stack.StackDetailResponseDto;
import com.project.easywork.client.domain.dto.stack.StackResponseDto;
import com.project.easywork.client.domain.dto.stack.StackUpdateRequestDto;
import com.project.easywork.client.domain.persistance.Stack;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder(),
    uses = {
        StackMeasurementMapper.class,
        PreventionMapper.class
    }
)
public interface StackMapper {
  Stack toEntity(StackCreateRequestDto dto);
  
  @Mapping(source = "workplace.id", target = "workplaceId")
  StackResponseDto toDto(Stack stack);
  
  @Mapping(source = ".", target = "stack")
  StackDetailResponseDto toDetailDto(Stack stack);
  
  List<StackResponseDto> toDtoList(List<Stack> stacks);
  
  @BeanMapping(
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
  )
  void updateStack(StackUpdateRequestDto dto, @MappingTarget Stack stack);
}