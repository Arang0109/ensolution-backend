package com.project.easywork.agency.repository;

import com.project.easywork.agency.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
  
  boolean existsByTeamId(Long teamId);
  boolean existsByTeamName(String teamName);
  boolean existsByTeamNameAndTeamIdNot(String teamName, Long teamId);
}