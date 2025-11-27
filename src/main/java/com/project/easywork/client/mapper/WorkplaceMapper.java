package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.workplace.WorkplaceDto;
import com.project.easywork.client.domain.dto.workplace.WorkplaceProfileDto;
import com.project.easywork.client.domain.dto.workplace.WorkplaceUpdateDto;
import com.project.easywork.client.domain.dto.workplace.WorkplaceDetailDto;
import com.project.easywork.client.domain.persistance.Workplace;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkplaceMapper {
  
  Workplace toEntity(WorkplaceDto dto);
  
  WorkplaceDto toDto(Workplace workplace);
  
  WorkplaceUpdateDto toUpdateDto(Workplace workplace);
  
  List<WorkplaceDto> toDtoList(List<Workplace> workplaces);
  
//  @Mapping(target = "company", source = "company")
//  @Mapping(target = "companyId", source = "company.id")
//  List<WorkplaceProfileDto> toProfileDtoList(List<Workplace> workplaces);
  
  @Mapping(target = "stacks", source = "stacks")
  WorkplaceDetailDto toDetailDto(Workplace workplace);
}