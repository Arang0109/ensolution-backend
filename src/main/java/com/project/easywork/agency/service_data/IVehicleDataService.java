package com.project.easywork.agency.service_data;

import com.project.easywork.agency.domain.entity.Vehicle;

import java.util.List;

public interface IVehicleDataService {
  Vehicle findById(Long vehicleId);
  Vehicle save(Vehicle vehicle);
  void deleteById(Long vehicleId);
  List<Vehicle> findAll();
  List<Vehicle> findVehiclesByTeamId(Long teamId);
}
