package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.facility.FacilityCreateRequestDto;
import com.project.easywork.client.domain.dto.facility.FacilityResponseDto;
import com.project.easywork.client.domain.dto.facility.FacilityUpdateRequestDto;
import com.project.easywork.client.domain.persistance.Facility;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder(),
    uses = {
    
    }
)
public interface FacilityMapper {
  Facility toEntity(FacilityCreateRequestDto dto);
  
  @Mapping(target = "preventionId", source = "prevention.id")
  FacilityResponseDto toDto(Facility facility);
  
  List<FacilityResponseDto> toDtoList(List<Facility> facilities);
  List<Facility> toEntityList(List<FacilityCreateRequestDto> facilities);
  
  void updateFacility(FacilityUpdateRequestDto dto, @MappingTarget Facility facility);
}