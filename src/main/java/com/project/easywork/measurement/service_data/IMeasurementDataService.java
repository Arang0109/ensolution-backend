package com.project.easywork.measurement.service_data;

import com.project.easywork.measurement.dto.document.MeasurementDoc;

import java.util.List;

public interface IMeasurementDataService {
  MeasurementDoc findById(String objectId);
  
  MeasurementDoc findByPlanId(Long planId);
  
  List<MeasurementDoc> findAll();
  
  MeasurementDoc save(MeasurementDoc doc);
  
  void deleteByPlanId(Long planId);
}
