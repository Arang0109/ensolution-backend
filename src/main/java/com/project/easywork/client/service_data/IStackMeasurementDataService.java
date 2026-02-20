package com.project.easywork.client.service_data;

import com.project.easywork.client.domain.persistance.StackMeasurement;

import java.util.List;

public interface IStackMeasurementDataService {
  StackMeasurement findById(Long stackMeasurementId);
  List<StackMeasurement> findByIdIn(List<Long> ids);
  StackMeasurement save(StackMeasurement stackMeasurement);
  List<StackMeasurement> saveAll(List<StackMeasurement> stackMeasurements);
  void deleteById(Long stackMeasurementId);
  List<StackMeasurement> findStackMeasurementsByStackId(Long stackId);
}
