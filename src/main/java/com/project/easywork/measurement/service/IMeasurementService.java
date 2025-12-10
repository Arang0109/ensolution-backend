package com.project.easywork.measurement.service;

import com.project.easywork.measurement.dto.MeasurementDraftUpdateCommandDto;
import com.project.easywork.measurement.dto.command.MeasurementCommandDto;
import com.project.easywork.measurement.dto.document.MeasurementDocument;

public interface IMeasurementService {
  void createDraft(Long scheduleId);
  void updateDraft(Long scheduleId, MeasurementDraftUpdateCommandDto request);
  void deleteDraft(Long scheduleId);
  void saveDocument(Long scheduleId, MeasurementCommandDto dto);
}
