package com.project.easywork.agency.repository;

import com.project.easywork.agency.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
  boolean existsByName(String name);
}