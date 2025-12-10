package com.project.easywork.measurement.pipeline.context;

import com.project.easywork.measurement.pipeline.domain.Measurement;
import com.project.easywork.measurement.dto.document.result.MeasurementResultDocument;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeasurementContext {
  
  private final Measurement domain;
  
  // 1. PressureConvertStep
  private Double atmospherePressure;
  private Double StaticPressure;
  private Double DynamicPressure;
  private Double gasMeterGaugePressure;
  
  // 2. MoistureCalculateStep
  private Double moistureRatio;
  
  // 3. GasDensityCalculateStep
  private Double gasDensity;
  
  // 4.
  private Double oxygenCorrected;
  
  private MeasurementResultDocument result;
  
  // 추가로 필요한 값들 계속 확장 가능
  public MeasurementContext(Measurement domain) {
    this.domain = domain;
  }
}

