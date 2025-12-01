package com.project.easywork.client.service_data;

import com.project.easywork.client.domain.persistance.StackMeasurement;

import java.util.List;

public interface IStackMeasurementDataService {
  StackMeasurement findById(Long stackMeasurementId);
  StackMeasurement save(StackMeasurement stackMeasurement);
  void deleteById(Long stackMeasurementId);
  List<StackMeasurement> findStackMeasurementsByStackId(Long stackId);
}
