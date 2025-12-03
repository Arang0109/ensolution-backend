package com.project.easywork.agency.service_data;

import com.project.easywork.agency.domain.entity.Team;

import java.util.List;

public interface ITeamDataService {
  Team findById(Long id);
  Team save(Team entity);
  void deleteById(Long id);
  List<Team> findAll();
}
