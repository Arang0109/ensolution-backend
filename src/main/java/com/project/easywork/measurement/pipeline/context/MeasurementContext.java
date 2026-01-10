package com.project.easywork.measurement.pipeline.context;

import com.project.easywork.measurement.pipeline.domain.Measurement;
import com.project.easywork.measurement.dto.document.result.MeasurementResultDocument;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class MeasurementContext {
  
  private final Measurement domain;
  
  // 1. PressureConvertStep
  private BigDecimal atmospherePressure;
  private BigDecimal gasMeterGaugePressure;
  
  // 2. MoistureCalculateStep
  private BigDecimal moistureRatio;
  
  // 3. GasDensityCalculateStep
  private BigDecimal gasDensity;
  
  // 4.
  private BigDecimal oxygenCorrected;
  private Integer pointCount;
  private List<BigDecimal> circularAxisCoords;
  
  private MeasurementResultDocument result;
  
  // 추가로 필요한 값들 계속 확장 가능
  public MeasurementContext(Measurement domain) {
    this.domain = domain;
  }
}

