package com.project.easywork.measurement.service;

import com.project.easywork.measurement.dto.snapshot.MeasurementSnapshot;
import com.project.easywork.plan.domain.dto.PlanCreateBundleD;

public interface IMeasurementQueryService {
  MeasurementSnapshot loadSnapshot(PlanCreateBundleD dto);
}
