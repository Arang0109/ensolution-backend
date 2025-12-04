package com.project.easywork.agency.service_data.impl;

import com.project.easywork.agency.domain.entity.Vehicle;
import com.project.easywork.agency.repository.VehicleRepository;
import com.project.easywork.agency.service_data.IVehicleDataService;
import com.project.easywork.common.exception.CustomException;
import com.project.easywork.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleDataService implements IVehicleDataService {
  
  private final VehicleRepository vehicleRepository;
  
  @Override
  public Vehicle findById(Long vehicleId) {
    return vehicleRepository.findById(vehicleId).orElseThrow(
        () -> new CustomException(ErrorCode.NOT_FOUND, "해당 차량을 찾을 수 없습니다.")
    );
  }
  
  @Override
  public Vehicle save(Vehicle vehicle) {
    return vehicleRepository.save(vehicle);
  }
  
  @Override
  public void deleteById(Long vehicleId) {
    vehicleRepository.deleteById(vehicleId);
  }
  
  @Override
  public List<Vehicle> findAll() {
    return vehicleRepository.findAll();
  }
  
  @Override
  public List<Vehicle> findVehiclesByTeamId(Long teamId) {
    return vehicleRepository.findVehiclesByTeamId(teamId);
  }
}
