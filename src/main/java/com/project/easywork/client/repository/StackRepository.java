package com.project.easywork.client.repository;

import com.project.easywork.client.domain.persistance.Stack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StackRepository extends JpaRepository<Stack, Long> {
  List<Stack> findStacksByWorkplaceId(Long workplaceId);
}