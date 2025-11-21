package com.project.easywork.client.mapper;

import com.project.easywork.client.dto.FacilityDto;
import com.project.easywork.client.entity.Facility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacilityMapper {
  @Mapping(target = "prevention.preventionId", source = "preventionId")
  Facility toEntity(FacilityDto dto);
  
  @Mapping(target = "preventionId", source = "prevention.preventionId")
  FacilityDto toDto(Facility entity);
  
  List<FacilityDto> toDtoList(List<Facility> facilities);
}