package com.project.easywork.agency.mapper;

import com.project.easywork.agency.domain.dto.VehicleCreateRequestDto;
import com.project.easywork.agency.domain.dto.VehicleResponseDto;
import com.project.easywork.agency.domain.entity.Vehicle;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder)
public interface VehicleMapper {
  @Mapping(target = "team.id", source = "teamId")
  Vehicle toEntity(VehicleCreateRequestDto dto);
  
  @Mapping(target = "teamId", source = "team.id")
  VehicleResponseDto toDto(Vehicle vehicle);
  
  List<VehicleResponseDto> toDtoList(List<Vehicle> vehicles);
}