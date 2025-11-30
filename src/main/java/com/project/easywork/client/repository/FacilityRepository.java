package com.project.easywork.client.repository;

import com.project.easywork.client.domain.persistance.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {
  List<Facility> findFacilitiesByPreventionId(Long preventionId);
}