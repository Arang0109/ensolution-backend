package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementCreateRequestDto;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementResponseDto;
import com.project.easywork.client.domain.persistance.StackMeasurement;
import com.project.easywork.client.mapper.StackMeasurementMapper;
import com.project.easywork.client.service.IStackMeasurementService;
import com.project.easywork.client.service_data.IStackMeasurementDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StackMeasurementService implements IStackMeasurementService {
  
  private final IStackMeasurementDataService stackMeasurementDataService;
  private final StackMeasurementMapper stackMeasurementMapper;
  
  @Override
  public StackMeasurementResponseDto registerStackMeasurement(StackMeasurementCreateRequestDto requestDto) {
    StackMeasurement stackMeasurement = stackMeasurementMapper.toEntityFromStackMeasurementCreateDto(requestDto);
    return stackMeasurementMapper.toDto(stackMeasurementDataService.save(stackMeasurement));
  }
}
