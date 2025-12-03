package com.project.easywork.agency.mapper;

import com.project.easywork.agency.domain.dto.VehicleDto;
import com.project.easywork.agency.domain.entity.Vehicle;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder)
public interface VehicleMapper {
  @Mapping(target = "team.id", source = "teamId")
  Vehicle toEntity(VehicleDto vehicleDto);
  
  @Mapping(target = "teamId", source = "team.id")
  VehicleDto toDto(Vehicle vehicle);
  
  List<VehicleDto> toDtoList(List<Vehicle> vehicles);
}