package com.project.easywork.client.repository;

import com.project.easywork.client.domain.persistance.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkplaceRepository extends JpaRepository<Workplace, Long> {
  List<Workplace> findWorkplacesByCompanyId(Long companyId);
}