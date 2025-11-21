package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.measurement.dto.document.result.MeasurementResultDocument;
import com.project.easywork.measurement.pipeline.context.MeasurementContext;

public class ResultBuildStep implements MeasurementStep {
  
  @Override
  public void execute(MeasurementContext context) {
    
    MeasurementResultDocument result = MeasurementResultDocument.builder()
        .weatherPressureMmHg(context.getWeatherPressureMmHg())
        .staticPressureMmHg(context.getStaticPressureMmHg())
        .gasEquipGaugePressureMmHg(context.getGasEquipGaugePressureMmHg())
        .moistureContent(context.getMoistureContent()) // or 2
        .gasDensity(context.getGasDensity())
        .oxygenCorrected(context.getOxygenCorrected())
        .build();
    
    context.setResult(result);
  }
}