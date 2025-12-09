package com.project.easywork.measurement.service_data;

import com.project.easywork.measurement.dto.document.MeasurementDocument;
import com.project.easywork.measurement.repository.MeasurementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeasurementDataService {
  
  private final MeasurementRepository measurementRepository;
  
  public MeasurementDocument findById(String objectId) {
    return measurementRepository.findById(objectId).orElseThrow(
        () -> new IllegalArgumentException("Measurement not found")
    );
  }
  
  public MeasurementDocument save(MeasurementDocument doc) {
    return measurementRepository.save(doc);
  }
}
