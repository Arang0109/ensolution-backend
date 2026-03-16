package com.project.easywork.measurement.service;

import com.project.easywork.measurement.dto.snapshot.DraftSnapshot;
import com.project.easywork.plan.domain.dto.PlanCreateBundleD;

public interface IMeasurementQueryService {
  DraftSnapshot createSnapshot(PlanCreateBundleD command);
}