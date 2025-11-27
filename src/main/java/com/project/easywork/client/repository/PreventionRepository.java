package com.project.easywork.client.repository;

import com.project.easywork.client.domain.persistance.Prevention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreventionRepository extends JpaRepository<Prevention, Long> {
}