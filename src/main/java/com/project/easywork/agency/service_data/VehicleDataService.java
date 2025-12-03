package com.project.easywork.agency.service_data;

import com.project.easywork.agency.domain.dto.VehicleDto;

import java.util.List;

public interface VehicleDataService {
  List<VehicleDto> findAll();
  
  List<VehicleDto> findVehiclesByTeam(Long teamId);
}
