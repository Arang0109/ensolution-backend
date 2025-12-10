package com.project.easywork.measurement.service_data.impl;

import com.project.easywork.measurement.dto.document.MeasurementDocument;
import com.project.easywork.measurement.repository.MeasurementRepository;
import com.project.easywork.measurement.service_data.IMeasurementDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeasurementDataService implements IMeasurementDataService {
  
  private final MeasurementRepository measurementRepository;
  
  @Override
  public MeasurementDocument findById(String objectId) {
    return measurementRepository.findById(objectId).orElseThrow(
        () -> new IllegalArgumentException("Measurement not found")
    );
  }
  
  @Override
  public MeasurementDocument findByScheduleId(Long scheduleId) {
    return measurementRepository.findByScheduleId(scheduleId).orElseThrow(
        () -> new IllegalArgumentException("Measurement not found")
    );
  }
  
  @Override
  public MeasurementDocument save(MeasurementDocument doc) {
    return measurementRepository.save(doc);
  }
  
  @Override
  public void deleteByScheduleId(Long scheduleId) {
    measurementRepository.deleteByScheduleId(scheduleId);
  }
}
