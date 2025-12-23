package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementCreateRequestDto;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementResponseDto;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementUpdateRequestDto;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.client.domain.persistance.StackMeasurement;
import com.project.easywork.client.mapper.StackMapper;
import com.project.easywork.client.mapper.StackMeasurementMapper;
import com.project.easywork.client.service.IStackMeasurementService;
import com.project.easywork.client.service_data.IStackDataService;
import com.project.easywork.client.service_data.IStackMeasurementDataService;
import com.project.easywork.pollutant.domain.persistance.Pollutant;
import com.project.easywork.pollutant.service_data.IPollutantDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StackMeasurementService implements IStackMeasurementService {
  
  private final IStackDataService stackDataService;
  private final IPollutantDataService pollutantDataService;
  private final IStackMeasurementDataService stackMeasurementDataService;
  private final StackMeasurementMapper stackMeasurementMapper;
  private final StackMapper stackMapper;
  
  @Override
  public StackMeasurementResponseDto registerStackMeasurement(StackMeasurementCreateRequestDto dto) {
    
    Stack stack = stackDataService.findById(dto.getStackId());
    Pollutant pollutant = pollutantDataService.findById(dto.getPollutantId());
    StackMeasurement stackMeasurement = stackMeasurementMapper.toEntity(dto);
    stackMeasurement.attachStack(stack);
    stackMeasurement.attachPollutant(pollutant);
    
    return stackMeasurementMapper.toDto(stackMeasurementDataService.save(stackMeasurement));
  }
  
  @Override
  public StackMeasurementResponseDto getStackMeasurement(Long id) {
    return stackMeasurementMapper.toDto(stackMeasurementDataService.findById(id));
  }
  
  @Override
  public List<StackMeasurementResponseDto> getStackMeasurementsByStack(Long stackId) {
    return stackMeasurementMapper.toDtoList(stackMeasurementDataService.findStackMeasurementsByStackId(stackId));
  }
  
  @Override
  public StackMeasurementResponseDto updateStackMeasurement(Long id, StackMeasurementUpdateRequestDto dto) {
    StackMeasurement stackMeasurement = stackMeasurementDataService.findById(id);
    stackMeasurementMapper.updateStackMeasurement(dto, stackMeasurement);
    return stackMeasurementMapper.toDto(stackMeasurement);
  }
  
  @Override
  public void removeStackMeasurement(Long id) {
    stackMeasurementDataService.deleteById(id);
  }
}
