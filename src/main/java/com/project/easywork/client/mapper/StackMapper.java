package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.stack.StackDto;
import com.project.easywork.client.domain.dto.stack.StackDetailDto;
import com.project.easywork.client.domain.persistance.Stack;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StackMapper {
  @Mapping(target = "workplace.workplaceId", source = "workplaceId")
  Stack toEntity(StackDto dto);
  
  @Mapping(target = "workplaceId", source = "workplace.workplaceId")
  StackDto toDto(Stack stack);
  
  List<StackDto> toDtoList(List<Stack> stacks);
  
  @Mapping(target = "preventions", source = "preventions")
  StackDetailDto toDetailDto(Stack stack);
}