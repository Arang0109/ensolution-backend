package com.project.easywork.agency.service;

import com.project.easywork.agency.domain.dto.VehicleResponseDto;

import java.util.List;

public interface VehicleService {
  List<VehicleResponseDto> getList();
  
  List<VehicleResponseDto> getListByTeam(Long teamId);
}
