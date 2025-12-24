package com.project.easywork.client.repository;

import com.project.easywork.client.domain.persistance.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
  boolean existsByName(String name);
  boolean existsByNameAndIdNot(String name, Long id);
  
  boolean existsByBizNumber(String bizNumber);
  boolean existsByBizNumberAndIdNot(String bizNumber, Long id);
}