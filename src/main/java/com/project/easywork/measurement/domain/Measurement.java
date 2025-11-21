package com.project.easywork.measurement.domain;

import com.project.easywork.measurement.dto.command.*;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Measurement {
  
  private final MeasurementCommandDto measurement;
  
  @Builder
  public Measurement(
      MeasurementCommandDto measurement
  ) {
    this.measurement = measurement;
  }
}