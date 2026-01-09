package com.project.easywork.plan.service_data.impl;

import com.project.easywork.plan.domain.persistance.PlanMeasurement;
import com.project.easywork.plan.repository.PlanMeasurementRepository;
import com.project.easywork.plan.service_data.IPlanMeasurementDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlanMeasurementDataService implements IPlanMeasurementDataService {
  
  private final PlanMeasurementRepository planMeasurementRepository;
  
  @Override
  public PlanMeasurement save(PlanMeasurement planMeasurement) {
    return planMeasurementRepository.save(planMeasurement);
  }
  
  @Override
  public void saveAll(List<PlanMeasurement> planMeasurements) {
    planMeasurementRepository.saveAll(planMeasurements);
  }
  
  @Override
  public List<PlanMeasurement> findAllByPlanId(Long planId) {
    return planMeasurementRepository.findSchedulePollutantsByPlanId(planId);
  }
}