package com.project.easywork.client.service_data.impl;

import com.project.easywork.client.domain.persistance.StackMeasurement;
import com.project.easywork.client.repository.StackMeasurementRepository;
import com.project.easywork.client.service_data.IStackMeasurementDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StackMeasurementDataService implements IStackMeasurementDataService {
  
  private final StackMeasurementRepository stackMeasurementRepository;
  
  @Override
  public StackMeasurement findById(Long stackMeasurementId) {
    return stackMeasurementRepository.findById(stackMeasurementId).orElse(null);
  }
  
  @Override
  public StackMeasurement save(StackMeasurement stackMeasurement) {
    return stackMeasurementRepository.save(stackMeasurement);
  }
  
  @Override
  public void deleteById(Long stackMeasurementId) {
    stackMeasurementRepository.deleteById(stackMeasurementId);
  }
  
  @Override
  public List<StackMeasurement> findStackMeasurementsByStackId(Long stackId) {
    return stackMeasurementRepository.findStackMeasurementsByStackId(stackId);
  }
}
