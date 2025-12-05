package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementCreateRequestDto;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementResponseDto;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementUpdateRequestDto;

import java.util.List;

public interface IStackMeasurementService {
  StackMeasurementResponseDto registerStackMeasurement(StackMeasurementCreateRequestDto dto);
  StackMeasurementResponseDto getStackMeasurement(Long id);
  List<StackMeasurementResponseDto> getStackMeasurementsByStack(Long stackId);
  StackMeasurementResponseDto updateStackMeasurement(Long id, StackMeasurementUpdateRequestDto dto);
  void removeStackMeasurement(Long id);
}