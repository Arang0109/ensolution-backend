package com.project.easywork.agency.mapper;

import com.project.easywork.agency.dto.VehicleDto;
import com.project.easywork.agency.entity.Vehicle;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder)
public interface VehicleMapper {
  @Mapping(target = "team.teamId", source = "teamId")
  Vehicle toEntity(VehicleDto vehicleDto);
  
  @Mapping(target = "teamId", source = "team.teamId")
  VehicleDto toDto(Vehicle vehicle);
  
  List<VehicleDto> toDtoList(List<Vehicle> vehicles);
}