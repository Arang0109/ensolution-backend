package com.project.easywork.agency.service_data;

import com.project.easywork.agency.domain.dto.VehicleResponseDto;

import java.util.List;

public interface VehicleDataService {
  List<VehicleResponseDto> findAll();
  
  List<VehicleResponseDto> findVehiclesByTeam(Long teamId);
}
