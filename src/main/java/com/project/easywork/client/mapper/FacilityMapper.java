package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.facility.FacilityDto;
import com.project.easywork.client.domain.persistance.Facility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacilityMapper {
  @Mapping(target = "prevention.id", source = "preventionId")
  Facility toEntity(FacilityDto dto);
  
  @Mapping(target = "preventionId", source = "prevention.id")
  FacilityDto toDto(Facility entity);
  
  List<FacilityDto> toDtoList(List<Facility> facilities);
}