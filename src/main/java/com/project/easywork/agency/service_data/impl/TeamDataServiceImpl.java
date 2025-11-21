package com.project.easywork.agency.service_data.impl;

import com.project.easywork.agency.dto.TeamDto;
import com.project.easywork.agency.entity.Team;
import com.project.easywork.agency.mapper.TeamMapper;
import com.project.easywork.agency.repository.TeamRepository;
import com.project.easywork.agency.service_data.TeamDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamDataServiceImpl implements TeamDataService {
  
  private final TeamRepository teamRepository;
  private final TeamMapper teamMapper;
  
  /** 전체 팀 목록 조회 (Entity → DTO 변환 포함) */
  @Override public List<TeamDto> findAll() { return teamMapper.toDtoList(teamRepository.findAll()); }
  
  @Override public TeamDto findById(Long teamId) { return teamMapper.toDto(teamRepository.findById(teamId).orElseThrow()); }
  
  @Override public void save(TeamDto teamDto) {
    Team team = teamMapper.toEntity(teamDto);
    teamRepository.save(team);
  }
  
  @Override public void delete(Long teamId) { teamRepository.deleteById(teamId); }
}
