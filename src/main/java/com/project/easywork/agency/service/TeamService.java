package com.project.easywork.agency.service;

import com.project.easywork.agency.dto.TeamDto;

import java.util.List;

public interface TeamService {
  List<TeamDto> getList();
  TeamDto get(Long teamId);
  void register(TeamDto teamDto);
  void update(TeamDto teamDto);
  void delete(Long teamId);
}