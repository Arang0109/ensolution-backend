package com.project.easywork.client.repository;

import com.project.easywork.client.domain.persistance.Prevention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreventionRepository extends JpaRepository<Prevention, Long> {
  List<Prevention> findByStack_IdOrderByNameAsc(Long stackId);
}