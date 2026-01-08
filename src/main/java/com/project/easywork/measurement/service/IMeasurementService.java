package com.project.easywork.measurement.service;

import com.project.easywork.measurement.dto.MeasurementDraftUpdateCommandDto;
import com.project.easywork.measurement.dto.command.MeasurementCommandDto;
import com.project.easywork.plan.domain.dto.PlanCreateBundleD;

public interface IMeasurementService {
  void createDraft(Long planId, PlanCreateBundleD dto);
  void updateDraft(Long planId, MeasurementDraftUpdateCommandDto request);
  void deleteDraft(Long planId);
  void saveDocument(Long planId, MeasurementCommandDto dto);
}
