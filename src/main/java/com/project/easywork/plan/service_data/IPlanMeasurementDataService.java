package com.project.easywork.plan.service_data;

import com.project.easywork.plan.domain.persistance.PlanMeasurement;

import java.util.List;

public interface IPlanMeasurementDataService {
  PlanMeasurement save(PlanMeasurement planMeasurement);
  void saveAll(List<PlanMeasurement> planMeasurements);
  List<PlanMeasurement> findAllByPlanId(Long scheduleId);
}
