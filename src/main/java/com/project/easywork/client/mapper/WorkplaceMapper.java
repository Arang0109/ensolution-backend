package com.project.easywork.client.mapper;

import com.project.easywork.client.dto.WorkplaceDto;
import com.project.easywork.client.dto.list.WorkplaceProfileDto;
import com.project.easywork.client.dto.update.WorkplaceUpdateDto;
import com.project.easywork.client.dto.view.WorkplaceDetailDto;
import com.project.easywork.client.entity.Workplace;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkplaceMapper {
  
  Workplace toEntity(WorkplaceDto dto);
  
  WorkplaceDto toDto(Workplace workplace);
  
  WorkplaceUpdateDto toUpdateDto(Workplace workplace);
  
  List<WorkplaceDto> toDtoList(List<Workplace> workplaces);
  
  @Mapping(target = "company", source = "company")
  List<WorkplaceProfileDto> toProfileDtoList(List<Workplace> workplaces);
  
  @Mapping(target = "stacks", source = "stacks")
  WorkplaceDetailDto toDetailDto(Workplace workplace);
}