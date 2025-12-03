package com.project.easywork.agency.service.impl;

import com.project.easywork.agency.domain.dto.TeamCreateRequestDto;
import com.project.easywork.agency.domain.dto.TeamDetailResponseDto;
import com.project.easywork.agency.domain.dto.TeamResponseDto;
import com.project.easywork.agency.domain.dto.TeamUpdateRequestDto;
import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.agency.mapper.TeamMapper;
import com.project.easywork.agency.service.ITeamService;
import com.project.easywork.agency.service_data.ITeamDataService;
import com.project.easywork.agency.validator.TeamValidator;
import com.project.easywork.user.domain.dto.UserResponseDto;
import com.project.easywork.user.mapper.UserMapper;
import com.project.easywork.user.service_data.IUserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService implements ITeamService {
  
  private final ITeamDataService teamDataService;
  private final TeamMapper teamMapper;
  private final TeamValidator teamValidator;
  private final UserMapper userMapper;
  private final IUserDataService userDataService;
  
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
    TeamResponseDto team = teamMapper.toDto(teamDataService.findById(id));
    List<UserResponseDto> users = userMapper.toResponseDtoList(
        userDataService.findUsersByTeamId(id)
    );
    
    return TeamDetailResponseDto.builder()
        .team(team)
        .users(users)
        .build();
  }
  
  @Override public TeamResponseDto update(Long id, TeamUpdateRequestDto dto) {
    teamValidator.validate(dto);
    
    Team team = teamDataService.findById(id);
    team.update(dto);
    
    return teamMapper.toDto(teamDataService.save(team));
  }
  
  @Override public void delete(Long id) {
    teamDataService.deleteById(id);
  }
}
