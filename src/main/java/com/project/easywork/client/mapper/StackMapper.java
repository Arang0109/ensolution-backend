package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.stack.StackCreateRequestDto;
import com.project.easywork.client.domain.dto.stack.StackDetailResponseDto;
import com.project.easywork.client.domain.dto.stack.StackResponseDto;
import com.project.easywork.client.domain.persistance.Stack;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StackMapper {
  @Mapping(target = "workplace.id", source = "workplaceId")
  Stack toEntityFromStackCreateDto(StackCreateRequestDto dto);
  
  @Mapping(target = "workplaceId", source = "workplace.id")
  StackResponseDto toDto(Stack stack);
  
  List<StackResponseDto> toDtoList(List<Stack> stacks);
}