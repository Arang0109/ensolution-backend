package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.facility.FacilityCreateRequestDto;
import com.project.easywork.client.domain.dto.facility.FacilityResponseDto;
import com.project.easywork.client.domain.dto.facility.FacilityUpdateRequestDto;
import com.project.easywork.client.domain.persistance.Facility;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder()
)
public interface FacilityMapper {
  Facility toEntity(FacilityCreateRequestDto dto);
  
  @Mapping(target = "preventionId", source = "prevention.id")
  FacilityResponseDto toDto(Facility facility);
  
  List<FacilityResponseDto> toDtoList(List<Facility> facilities);
  List<Facility> toEntityList(List<FacilityCreateRequestDto> facilities);
  
  @BeanMapping(
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
  )
  void updateFacility(FacilityUpdateRequestDto dto, @MappingTarget Facility facility);
}