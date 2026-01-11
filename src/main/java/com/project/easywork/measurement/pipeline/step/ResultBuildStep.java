package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.measurement.dto.document.result.MeasurementResultDoc;
import com.project.easywork.measurement.pipeline.context.MeasurementContext;

public class ResultBuildStep implements MeasurementStep {
  
  @Override
  public void execute(MeasurementContext context) {
    
    MeasurementResultDoc result = MeasurementResultDoc.builder()
        .atmospherePressure(context.getAtmospherePressure())
        .gasMeterGaugePressure(context.getGasMeterGaugePressure())
        .moistureRatio(context.getMoistureRatio()) // or 2
        .gasDensity(context.getGasDensity())
        .oxygenCorrected(context.getOxygenCorrected())
        .build();
    
    context.setResult(result);
  }
}