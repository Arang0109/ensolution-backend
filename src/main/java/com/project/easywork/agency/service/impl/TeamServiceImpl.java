package com.project.easywork.agency.service.impl;

import com.project.easywork.agency.dto.TeamDto;
import com.project.easywork.agency.service.TeamService;
import com.project.easywork.agency.service_data.TeamDataService;
import com.project.easywork.common.validator.TeamValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
  
  private final TeamDataService teamDataService;
  private final TeamValidator teamValidator;
  
  @Override public List<TeamDto> getList() {
    return teamDataService.findAll();
  }
  
  @Override public TeamDto get(Long teamId) {
    teamValidator.validateFind(teamId);
    return teamDataService.findById(teamId);
  }
  
  @Override public void register(TeamDto teamDto) {
    teamValidator.validateSave(teamDto);
    teamDataService.save(teamDto);
  }
  
  @Override public void update(TeamDto teamDto) {
    teamValidator.validateUpdate(teamDto);
    teamDataService.save(teamDto);
  }
  
  @Override public void delete(Long teamId) {
    teamValidator.validateFind(teamId);
    teamDataService.delete(teamId);
  }
}
