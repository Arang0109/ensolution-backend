package com.project.easywork.measurement.service_data;

import com.project.easywork.measurement.dto.document.MeasurementDocument;

public interface IMeasurementDataService {
  MeasurementDocument findById(String objectId);
  
  MeasurementDocument findByPlanId(Long planId);
  
  MeasurementDocument save(MeasurementDocument doc);
  
  void deleteByPlanId(Long planId);
}
