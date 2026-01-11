package com.project.easywork.measurement.service_data;

import com.project.easywork.measurement.dto.document.MeasurementDoc;

public interface IMeasurementDataService {
  MeasurementDoc findById(String objectId);
  
  MeasurementDoc findByPlanId(Long planId);
  
  MeasurementDoc save(MeasurementDoc doc);
  
  void deleteByPlanId(Long planId);
}
