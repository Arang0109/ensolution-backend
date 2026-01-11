package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.measurement.pipeline.context.MeasurementContext;
import com.project.easywork.measurement.pipeline.domain.Measurement;

public class PitotCoefficientStep implements MeasurementStep {
  @Override
  public void execute(MeasurementContext context) {
    Measurement d = context.getDomain();
    
    
  }
}