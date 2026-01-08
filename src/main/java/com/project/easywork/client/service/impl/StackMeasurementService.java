package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.stack.MeasurementListD;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementCreateD;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementD;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementUpdateD;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.client.domain.persistance.StackMeasurement;
import com.project.easywork.client.mapper.StackMeasurementMapper;
import com.project.easywork.client.service.IStackMeasurementService;
import com.project.easywork.client.service_data.IStackMeasurementDataService;
import com.project.easywork.common.resolver.DomainEntityResolver;
import com.project.easywork.pollutant.domain.persistance.Pollutant;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StackMeasurementService implements IStackMeasurementService {
  
  private final IStackMeasurementDataService stackMeasurementDataService;
  private final StackMeasurementMapper stackMeasurementMapper;
  
  private final DomainEntityResolver domainEntityResolver;
  
  private final EntityManager entityManager;
  
  @Override
  public StackMeasurementD registerStackMeasurement(StackMeasurementCreateD dto) {
    
    Stack stack = domainEntityResolver.getStackOrThrow(dto.getStackId());
    Pollutant pollutant = domainEntityResolver.getPollutantOrThrow(dto.getPollutantId());
    StackMeasurement stackMeasurement = stackMeasurementMapper.toEntity(dto);
    stackMeasurement.attachStack(stack);
    stackMeasurement.attachPollutant(pollutant);
    
    return stackMeasurementMapper.toDto(stackMeasurementDataService.save(stackMeasurement));
  }
  
  @Override
  public StackMeasurementD getStackMeasurement(Long id) {
    StackMeasurement stackMeasurement = domainEntityResolver.getStackMeasurementOrThrow(id);
    return stackMeasurementMapper.toDto(stackMeasurement);
  }
  
  @Override
  public List<MeasurementListD> getStackMeasurementsByStack(Long stackId) {
    return stackMeasurementMapper.toMeasurementDtoList(stackMeasurementDataService.findStackMeasurementsByStackId(stackId));
  }
  
  @Override
  public StackMeasurementD updateStackMeasurement(Long id, StackMeasurementUpdateD dto) {
    StackMeasurement stackMeasurement = domainEntityResolver.getStackMeasurementOrThrow(id);
    stackMeasurement.update(dto);
    
    entityManager.flush();
    
    return stackMeasurementMapper.toDto(stackMeasurement);
  }
  
  @Override
  public void removeStackMeasurement(Long id) {
    domainEntityResolver.getStackMeasurementOrThrow(id);
    stackMeasurementDataService.deleteById(id);
  }
}
