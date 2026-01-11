package com.project.easywork.measurement.service.impl;

import com.project.easywork.measurement.dto.command.MeasurementCommandD;
import com.project.easywork.measurement.dto.document.result.MeasurementResultDoc;
import com.project.easywork.measurement.pipeline.MeasurementPipeline;
import com.project.easywork.measurement.pipeline.context.MeasurementContext;
import com.project.easywork.measurement.pipeline.domain.Measurement;
import com.project.easywork.measurement.pipeline.step.GasDensityCalculateStep;
import com.project.easywork.measurement.pipeline.step.MoistureCalculateStep;
import com.project.easywork.measurement.pipeline.step.PressureConvertStep;
import com.project.easywork.measurement.pipeline.step.ResultBuildStep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeasurementResultProcessor {
  public MeasurementResultDoc process(MeasurementCommandD dto) {
    
    Measurement domain = Measurement.builder()
        .measurement(dto)
        .build();
    
    MeasurementContext context = new MeasurementContext(domain);
    
    MeasurementPipeline pipeline = new MeasurementPipeline()
        .addStep(new PressureConvertStep())
        .addStep(new MoistureCalculateStep())
        .addStep(new GasDensityCalculateStep())
        .addStep(new ResultBuildStep());
    
    pipeline.execute(context);
    
    return context.getResult();
  }
}
