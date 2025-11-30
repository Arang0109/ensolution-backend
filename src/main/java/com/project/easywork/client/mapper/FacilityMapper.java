package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.facility.FacilityCreateRequestDto;
import com.project.easywork.client.domain.dto.facility.FacilityResponseDto;
import com.project.easywork.client.domain.persistance.Facility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacilityMapper {
  @Mapping(target = "prevention.id", source = "preventionId")
  Facility toEntityFromFacilityCreateDto(FacilityCreateRequestDto dto);
  
  @Mapping(target = "preventionId", source = "prevention.id")
  FacilityResponseDto toDto(Facility facility);
  
  List<FacilityResponseDto> toDtoList(List<Facility> facilities);
}