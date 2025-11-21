package com.project.easywork.client.mapper;

import com.project.easywork.client.dto.StackDto;
import com.project.easywork.client.dto.view.StackDetailDto;
import com.project.easywork.client.entity.Stack;
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