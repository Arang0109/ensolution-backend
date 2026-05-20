package com.project.easywork.agency.service;

import com.project.easywork.agency.domain.dto.TeamCreateD;
import com.project.easywork.agency.domain.dto.TeamD;
import com.project.easywork.agency.domain.dto.TeamUpdateCommand;
import com.project.easywork.agency.domain.dto.TeamUpdateD;
import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.agency.mapper.TeamMapper;
import com.project.easywork.agency.service_data.TeamDataService;
import com.project.easywork.agency.validator.TeamValidator;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {
  
  private final TeamMapper teamMapper;
  private final TeamValidator teamValidator;
  
  private final TeamDataService teamDataService;
  
  private final EntityManager entityManager;
  
  public TeamD register(TeamCreateD dto) {
    teamValidator.validate(dto);
    
    Team team = teamMapper.toEntity(dto);
    
    return teamMapper.toDto(teamDataService.save(team));
  }
  
  @Transactional(readOnly = true)
  public List<TeamD> getList() {
    return teamMapper.toDtoList(teamDataService.findAll());
  }
  
  @Transactional(readOnly = true)
  public TeamD get(Long id) {
    return teamMapper.toDto(teamDataService.findById(id));
  }
  
  public TeamD update(Long id, TeamUpdateD dto) {
    Team team = teamDataService.findById(id);
    
    teamValidator.validateUpdate(dto, team);
    
    TeamUpdateCommand command = teamMapper.toUpdateCommand(dto);
    team.update(command);
    
    entityManager.flush();
    
    return teamMapper.toDto(team);
  }
  
  public void delete(Long id) {
    teamDataService.deleteById(id);
  }
}
