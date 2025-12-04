package com.project.easywork.agency.service_data;

import com.project.easywork.agency.domain.entity.Team;

import java.util.List;

public interface ITeamDataService {
  Team findById(Long teamId);
  Team save(Team team);
  void deleteById(Long teamId);
  List<Team> findAll();
}
