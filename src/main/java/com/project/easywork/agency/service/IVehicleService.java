package com.project.easywork.agency.service;

import com.project.easywork.agency.domain.dto.VehicleCreateRequestDto;
import com.project.easywork.agency.domain.dto.VehicleResponseDto;
import com.project.easywork.agency.domain.dto.VehicleUpdateRequestDto;

import java.util.List;

public interface IVehicleService {
  List<VehicleResponseDto> getList();
  VehicleResponseDto get(Long vehicleId);
  VehicleResponseDto register(VehicleCreateRequestDto dto);
  VehicleResponseDto update(Long vehicleId, VehicleUpdateRequestDto dto);
  void delete(Long vehicleId);
}
