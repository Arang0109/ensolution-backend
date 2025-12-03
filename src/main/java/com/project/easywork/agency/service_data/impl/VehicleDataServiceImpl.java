package com.project.easywork.agency.service_data.impl;

import com.project.easywork.agency.domain.dto.VehicleDto;
import com.project.easywork.agency.mapper.VehicleMapper;
import com.project.easywork.agency.repository.VehicleRepository;
import com.project.easywork.agency.service_data.VehicleDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleDataServiceImpl implements VehicleDataService {
  
  private final VehicleRepository vehicleRepository;
  private final VehicleMapper vehicleMapper;
  
  @Override
  public List<VehicleDto> findAll() {
    return vehicleMapper.toDtoList(vehicleRepository.findAll());
  }
  
  @Override
  public List<VehicleDto> findVehiclesByTeam(Long teamId) {
    return vehicleMapper.toDtoList(vehicleRepository.findByTeam_Id(teamId));
  }
}
