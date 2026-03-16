package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.stack.MeasurementListD;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementCreateD;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementD;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementUpdateD;

import java.util.List;

public interface IStackMeasurementService {
  List<StackMeasurementD> registerStackMeasurement(List<StackMeasurementCreateD> dtos);
  StackMeasurementD getStackMeasurement(Long id);
  List<MeasurementListD> getStackMeasurementsByStack(Long stackId);
  StackMeasurementD updateStackMeasurement(Long id, StackMeasurementUpdateD dto);
  void removeStackMeasurement(Long id);
}