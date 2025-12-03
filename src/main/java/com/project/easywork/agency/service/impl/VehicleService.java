package com.project.easywork.agency.service.impl;

import com.project.easywork.agency.domain.dto.VehicleCreateRequestDto;
import com.project.easywork.agency.domain.dto.VehicleResponseDto;
import com.project.easywork.agency.domain.dto.VehicleUpdateRequestDto;
import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.agency.domain.entity.Vehicle;
import com.project.easywork.agency.mapper.VehicleMapper;
import com.project.easywork.agency.service.IVehicleService;
import com.project.easywork.agency.service_data.ITeamDataService;
import com.project.easywork.agency.service_data.IVehicleDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VehicleService implements IVehicleService {
  private final IVehicleDataService vehicleDataService;
  private final ITeamDataService teamDataService;
  private final VehicleMapper vehicleMapper;
  
  @Override
  public VehicleResponseDto register(VehicleCreateRequestDto dto) {
    Vehicle vehicle = vehicleMapper.toEntity(dto);
    
    return vehicleMapper.toDto(
        vehicleDataService.save(vehicle)
    );
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<VehicleResponseDto> getList() { return vehicleMapper.toDtoList(vehicleDataService.findAll()); }
  
  @Override
  @Transactional(readOnly = true)
  public VehicleResponseDto get(Long vehicleId) {
    return vehicleMapper.toDto(
        vehicleDataService.findById(vehicleId)
    );
  }
  
  @Override
  public VehicleResponseDto update(Long vehicleId, VehicleUpdateRequestDto dto) {
    Vehicle vehicle = vehicleDataService.findById(vehicleId);
    Team team = null;
    if (dto.getTeamId() != null) {
      team = teamDataService.findById(dto.getTeamId());
    }
    vehicle.update(team, dto);
    return vehicleMapper.toDto(vehicle);
  }
  
  @Override
  public void delete(Long vehicleId) {
    vehicleDataService.deleteById(vehicleId);
  }
}
