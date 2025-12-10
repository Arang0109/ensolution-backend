package com.project.easywork.measurement.dto.document.result;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MeasurementResultDocument {
  
  private Double atmospherePressure;
  private Double staticPressure;
  private Double dynamicPressure;
  private Double gasMeterGaugePressure;
  
  private Double moistureRatio;
  private Double gasDensity;
  private Double oxygenCorrected;
  
  private Integer pointCount;
  
}
