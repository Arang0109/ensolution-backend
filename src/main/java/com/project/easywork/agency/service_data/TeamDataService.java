package com.project.easywork.agency.service_data;

import com.project.easywork.agency.dto.TeamDto;

import java.util.List;

public interface TeamDataService {
  List<TeamDto> findAll();
  
  TeamDto findById(Long teamId);
  
  void save(TeamDto teamDto);
  
  void delete(Long teamId);
}
