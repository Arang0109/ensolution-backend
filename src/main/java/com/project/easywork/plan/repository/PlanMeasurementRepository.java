package com.project.easywork.plan.repository;

import com.project.easywork.plan.domain.persistance.PlanMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanMeasurementRepository extends JpaRepository<PlanMeasurement, Long> {
  List<PlanMeasurement> findSchedulePollutantsByPlanId(Long planId);
}