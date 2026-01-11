package com.project.easywork.measurement.service_data.impl;

import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.measurement.repository.MeasurementRepository;
import com.project.easywork.measurement.service_data.IMeasurementDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeasurementDataService implements IMeasurementDataService {
  
  private final MeasurementRepository measurementRepository;
  
  @Override
  public MeasurementDoc findById(String objectId) {
    return measurementRepository.findById(objectId).orElseThrow(
        () -> new IllegalArgumentException("Measurement not found")
    );
  }
  
  @Override
  public MeasurementDoc findByPlanId(Long planId) {
    return measurementRepository.findByPlanId(planId).orElseThrow(
        () -> new IllegalArgumentException("Measurement not found")
    );
  }
  
  @Override
  public MeasurementDoc save(MeasurementDoc doc) {
    return measurementRepository.save(doc);
  }
  
  @Override
  public void deleteByPlanId(Long planId) {
    measurementRepository.deleteByPlanId(planId);
  }
}
