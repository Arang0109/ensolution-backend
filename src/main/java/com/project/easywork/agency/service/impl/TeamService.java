package com.project.easywork.agency.service.impl;

import com.project.easywork.agency.domain.dto.*;
import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.agency.mapper.TeamMapper;
import com.project.easywork.agency.service.ITeamService;
import com.project.easywork.agency.service_data.ITeamDataService;
import com.project.easywork.agency.validator.TeamValidator;
import com.project.easywork.equipment.domain.persistance.Equipment;
import com.project.easywork.equipment.domain.persistance.PitotTube;
import com.project.easywork.equipment.service_data.IEquipmentDataService;
import com.project.easywork.equipment.service_data.IPitotTubeDataService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
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
  private final IEquipmentDataService equipmentDataService;
  private final IPitotTubeDataService pitotTubeDataService;
  
  private final EntityManager entityManager;
  
  @Override
  public TeamResponseDto register(TeamCreateRequestDto dto) {
    teamValidator.validate(dto);
    
    Team team = teamMapper.toEntity(dto);
    
    return teamMapper.toDto(teamDataService.save(team));
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<TeamResponseDto> getList() {
    return teamMapper.toDtoList(teamDataService.findAll());
  }
  
  @Override
  @Transactional(readOnly = true)
  public TeamDetailResponseDto get(Long id) {
    return teamMapper.toDetailDto(teamDataService.findById(id));
  }
  
  @Override public TeamResponseDto update(Long id, TeamUpdateRequestDto dto) {
    teamValidator.validate(dto);
    Team team = teamDataService.findById(id);
    team.update(dto);
    
    entityManager.flush();
    
    return teamMapper.toDto(team);
  }
  
  @Override
  public TeamResponseDto updateParticularEquip(Long teamId, @Nullable Long equipmentId) {
    Team team = teamDataService.findById(teamId);
    
    Equipment equipment = equipmentId == null
        ? null
        : equipmentDataService.findById(equipmentId);
    
    team.changeParticularEquip(equipment);
    
    entityManager.flush();
    
    return teamMapper.toDto(team);
  }
  
  @Override
  public TeamResponseDto updatePitotTube(Long teamId, @Nullable Long pitotTubeId) {
    Team team = teamDataService.findById(teamId);
    
    PitotTube pitotTube = pitotTubeId == null
        ? null
        : pitotTubeDataService.findById(pitotTubeId);
    
    team.changePitotTube(pitotTube);
    
    entityManager.flush();
    
    return teamMapper.toDto(team);
  }
  
  @Override public void delete(Long id) {
    teamDataService.deleteById(id);
  }
}
