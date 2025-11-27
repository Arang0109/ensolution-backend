package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.workplace.WorkplaceCreateRequestDto;
import com.project.easywork.client.domain.dto.workplace.WorkplaceResponseDto;
import com.project.easywork.client.domain.persistance.Workplace;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkplaceMapper {
  
  Workplace toEntityFromWorkplaceCreateDto(WorkplaceCreateRequestDto dto);
  
  @Mapping(target = "companyId", source = "company.id")
  WorkplaceResponseDto toDto(Workplace workplace);
  
  List<WorkplaceResponseDto> toDtoList(List<Workplace> workplaces);
}