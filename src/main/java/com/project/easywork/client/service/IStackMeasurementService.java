package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementCreateRequestDto;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementResponseDto;

public interface IStackMeasurementService {
  StackMeasurementResponseDto registerStackMeasurement(StackMeasurementCreateRequestDto requestDto);
}