package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.target.TargetCreateD;
import com.project.easywork.client.domain.dto.target.TargetD;
import com.project.easywork.client.domain.persistance.Target;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder()
)
public interface TargetMapper {
  Target toEntity(TargetCreateD dto);
  
  @Mapping(target = "preventionId", source = "prevention.id")
  TargetD toDto(Target target);
  
  List<TargetD> toDtoList(List<Target> targets);
  List<Target> toEntityList(List<TargetCreateD> targets);
}