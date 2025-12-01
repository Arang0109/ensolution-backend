package com.project.easywork.client.repository;

import com.project.easywork.client.domain.persistance.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TargetRepository extends JpaRepository<Target, Long> {
  List<Target> findTargetsByPreventionId(Long preventionId);
}