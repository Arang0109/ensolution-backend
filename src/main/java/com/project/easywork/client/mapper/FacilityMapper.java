package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.facility.FacilityCreateD;
import com.project.easywork.client.domain.dto.facility.FacilityD;
import com.project.easywork.client.domain.persistance.Facility;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder()
)
public interface FacilityMapper {
  Facility toEntity(FacilityCreateD dto);
  
  @Mapping(target = "preventionId", source = "prevention.id")
  FacilityD toDto(Facility facility);
  
  List<FacilityD> toDtoList(List<Facility> facilities);
  List<Facility> toEntityList(List<FacilityCreateD> facilities);
}