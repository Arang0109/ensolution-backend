package com.project.easywork.measurement.service_data;

import com.project.easywork.measurement.dto.document.MeasurementDocument;

public interface IMeasurementDataService {
  MeasurementDocument findById(String objectId);
  
  MeasurementDocument findByScheduleId(Long scheduleId);
  
  MeasurementDocument save(MeasurementDocument doc);
  
  void deleteByScheduleId(Long scheduleId);
}
