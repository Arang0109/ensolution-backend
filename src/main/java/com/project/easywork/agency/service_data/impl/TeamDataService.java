package com.project.easywork.agency.service_data.impl;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.agency.repository.TeamRepository;
import com.project.easywork.agency.service_data.ITeamDataService;
import com.project.easywork.common.exception.CustomException;
import com.project.easywork.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamDataService implements ITeamDataService {
  
  private final TeamRepository teamRepository;
  
  @Override public Team findById(Long teamId) {
    return teamRepository.findById(teamId).orElseThrow(
        () -> new CustomException(ErrorCode.NOT_FOUND, "해당 팀을 찾을 수 없습니다.")
    );
  }
  
  @Override public Team save(Team team) { return teamRepository.save(team); }
  
  @Override public void deleteById(Long teamId) {
    if (!teamRepository.existsById(teamId)) {
      throw new CustomException(ErrorCode.NOT_FOUND, "삭제할 팀이 존재하지 않습니다.");
    }
    teamRepository.deleteById(teamId);
  }
  
  @Override public List<Team> findAll() { return teamRepository.findAll(); }
}