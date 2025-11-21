package com.project.easywork.measurement.pipeline;

import com.project.easywork.measurement.pipeline.context.MeasurementContext;
import com.project.easywork.measurement.pipeline.step.MeasurementStep;

import java.util.ArrayList;
import java.util.List;

public class MeasurementPipeline {
  
  private final List<MeasurementStep> steps = new ArrayList<>();
  
  public MeasurementPipeline addStep(MeasurementStep step) {
    this.steps.add(step);
    return this; // 체이닝
  }
  
  public void execute(MeasurementContext context) {
    for (MeasurementStep step : steps) {
      step.execute(context);
    }
  }
}