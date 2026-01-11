package com.project.easywork.measurement.service;

import com.project.easywork.measurement.dto.DraftUpdateCommandD;
import com.project.easywork.measurement.dto.command.MeasurementCommandD;
import com.project.easywork.plan.domain.dto.PlanCreateBundleD;

public interface IMeasurementService {
  void createDraft(Long planId, PlanCreateBundleD dto);
  void updateDraft(Long planId, DraftUpdateCommandD request);
  void deleteDraft(Long planId);
  void saveDocument(Long planId, MeasurementCommandD dto);
}
