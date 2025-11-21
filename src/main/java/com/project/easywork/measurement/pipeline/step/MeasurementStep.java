package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.measurement.pipeline.context.MeasurementContext;

public interface MeasurementStep {
  void execute(MeasurementContext context);
}