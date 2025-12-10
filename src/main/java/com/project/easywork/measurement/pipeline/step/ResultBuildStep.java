package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.measurement.dto.document.result.MeasurementResultDocument;
import com.project.easywork.measurement.pipeline.context.MeasurementContext;

public class ResultBuildStep implements MeasurementStep {
  
  @Override
  public void execute(MeasurementContext context) {
    
    MeasurementResultDocument result = MeasurementResultDocument.builder()
        .atmospherePressure(context.getAtmospherePressure())
        .staticPressure(context.getStaticPressure())
        .dynamicPressure(context.getDynamicPressure())
        .gasMeterGaugePressure(context.getGasMeterGaugePressure())
        .moistureRatio(context.getMoistureRatio()) // or 2
        .gasDensity(context.getGasDensity())
        .oxygenCorrected(context.getOxygenCorrected())
        .pointCount(context.getPointCount())
        .build();
    
    context.setResult(result);
  }
}