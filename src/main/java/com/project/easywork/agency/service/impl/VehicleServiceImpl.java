package com.project.easywork.agency.service.impl;

import com.project.easywork.agency.domain.dto.VehicleResponseDto;
import com.project.easywork.agency.service.VehicleService;
import com.project.easywork.agency.service_data.VehicleDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
  private final VehicleDataService vehicleDataService;
  
  @Override
  public List<VehicleResponseDto> getList() { return vehicleDataService.findAll(); }
  
  @Override
  public List<VehicleResponseDto> getListByTeam(Long teamId) { return vehicleDataService.findVehiclesByTeam(teamId); }
}
