package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.stack.StackCreateD;
import com.project.easywork.client.domain.dto.stack.StackDetailD;
import com.project.easywork.client.domain.dto.stack.StackD;
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
  Stack toEntity(StackCreateD dto);
  
  @Mapping(source = "workplace.id", target = "workplaceId")
  StackD toDto(Stack stack);
  
  @Mapping(source = ".", target = "stack")
  StackDetailD toDetailDto(Stack stack);
  
  List<StackD> toDtoList(List<Stack> stacks);
}