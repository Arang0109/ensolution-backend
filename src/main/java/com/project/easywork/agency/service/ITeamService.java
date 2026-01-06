package com.project.easywork.agency.service;

import com.project.easywork.agency.domain.dto.TeamCreateRequestDto;
import com.project.easywork.agency.domain.dto.TeamDetailResponseDto;
import com.project.easywork.agency.domain.dto.TeamResponseDto;
import com.project.easywork.agency.domain.dto.TeamUpdateRequestDto;

import java.util.List;

public interface ITeamService {
  List<TeamResponseDto> getList();
  TeamDetailResponseDto get(Long teamId);
  TeamResponseDto register(TeamCreateRequestDto dto);
  TeamResponseDto update(Long teamId, TeamUpdateRequestDto dto);
  void delete(Long teamId);
  
  TeamResponseDto updateParticularEquip(Long teamId, Long equipmentId);
  TeamResponseDto updatePitotTube(Long teamId, Long pitotTubeId);
}