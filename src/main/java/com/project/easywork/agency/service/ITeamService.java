package com.project.easywork.agency.service;

import com.project.easywork.agency.domain.dto.TeamCreateD;
import com.project.easywork.agency.domain.dto.TeamDetailD;
import com.project.easywork.agency.domain.dto.TeamD;
import com.project.easywork.agency.domain.dto.TeamUpdateD;

import java.util.List;

public interface ITeamService {
  List<TeamD> getList();
  TeamDetailD get(Long teamId);
  TeamD register(TeamCreateD dto);
  TeamD update(Long teamId, TeamUpdateD dto);
  void delete(Long teamId);
}