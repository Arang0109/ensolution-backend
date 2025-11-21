package com.project.easywork.agency.service;

import com.project.easywork.agency.dto.VehicleDto;

import java.util.List;

public interface VehicleService {
  List<VehicleDto> getList();
  
  List<VehicleDto> getListByTeam(Long teamId);
}
