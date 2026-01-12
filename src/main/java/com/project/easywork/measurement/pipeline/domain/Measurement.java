package com.project.easywork.measurement.pipeline.domain;

import com.project.easywork.measurement.dto.document.MeasurementDoc;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Measurement {
  private MeasurementDoc measurement;
  
  public void updateMeasurement(MeasurementDoc measurement) {
    this.measurement = measurement;
  }
}