package com.project.easywork.measurement.pipeline.context;

import com.project.easywork.measurement.pipeline.domain.Measurement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeasurementContext {
  
  private final Measurement domain;
  
  public MeasurementContext(Measurement domain) {
    this.domain = domain;
  }
}