package com.project.easywork.agency.service.impl;

import com.project.easywork.agency.domain.dto.*;
import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.agency.mapper.TeamMapper;
import com.project.easywork.agency.service.ITeamService;
import com.project.easywork.agency.service_data.ITeamDataService;
import com.project.easywork.agency.validator.TeamValidator;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService implements ITeamService {
  
  private final TeamMapper teamMapper;
  private final TeamValidator teamValidator;
  
  private final ITeamDataService teamDataService;
  
  private final EntityManager entityManager;
  
  @Override
  public TeamD register(TeamCreateD dto) {
    teamValidator.validate(dto);
    
    Team team = teamMapper.toEntity(dto);
    
    return teamMapper.toDto(teamDataService.save(team));
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<TeamD> getList() {
    return teamMapper.toDtoList(teamDataService.findAll());
  }
  
  @Override
  @Transactional(readOnly = true)
  public TeamDetailD get(Long id) {
    return teamMapper.toDetailDto(teamDataService.findById(id));
  }
  
  @Override public TeamD update(Long id, TeamUpdateD dto) {
    Team team = teamDataService.findById(id);
    team.update(dto);
    
    entityManager.flush();
    
    return teamMapper.toDto(team);
  }
  
  @Override public void delete(Long id) {
    teamDataService.deleteById(id);
  }
}
