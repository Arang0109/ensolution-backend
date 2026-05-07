package com.project.easywork.measurement.service;

import com.project.easywork.measurement.domain.dto.draft_source.DraftSource;
import com.project.easywork.plan.domain.dto.PlanCreateBundleD;

public interface IMeasurementQueryService {
  DraftSource createDraftSource(PlanCreateBundleD command);
}