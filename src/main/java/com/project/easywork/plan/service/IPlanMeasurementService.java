package com.project.easywork.plan.service;

import com.project.easywork.plan.domain.dto.MeasurementItemsCreateD;
import com.project.easywork.plan.domain.dto.PlanMeasurementsD;

import java.util.List;

public interface IPlanMeasurementService {
  PlanMeasurementsD register(MeasurementItemsCreateD dto);
  void registerAll(Long planId, List<Long> measurementIds);
}
