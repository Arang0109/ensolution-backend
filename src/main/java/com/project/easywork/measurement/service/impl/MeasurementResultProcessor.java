package com.project.easywork.measurement.service.impl;

import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.measurement.pipeline.MeasurementPipeline;
import com.project.easywork.measurement.pipeline.context.MeasurementContext;
import com.project.easywork.measurement.pipeline.domain.Measurement;
import com.project.easywork.measurement.pipeline.step.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeasurementResultProcessor {
  public MeasurementDoc process(MeasurementDoc measurementDoc) {
    
    Measurement domain = Measurement.builder()
        .measurement(measurementDoc)
        .build();
    
    MeasurementContext context = new MeasurementContext(domain);
    
    MeasurementPipeline pipeline = new MeasurementPipeline()
        .addStep(new WeatherStep())
        .addStep(new MoistureStep())
        .addStep(new ExhaustGasStep())
        .addStep(new MeasurementPointStep());
    
    pipeline.execute(context);
    
    return context.getDomain().getMeasurement();
  }
}
