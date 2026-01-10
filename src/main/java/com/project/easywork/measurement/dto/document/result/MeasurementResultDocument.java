package com.project.easywork.measurement.dto.document.result;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Builder(toBuilder = true)
public class MeasurementResultDocument {
  
  private BigDecimal atmospherePressure;
  private BigDecimal staticPressure;
  private BigDecimal dynamicPressure;
  private BigDecimal gasMeterGaugePressure;
  
  private BigDecimal moistureRatio;
  private BigDecimal gasDensity;
  private BigDecimal oxygenCorrected;
  
  private Integer pointCount;
  
  public MeasurementResultDocument normalize() {
    return this.toBuilder()
        .atmospherePressure(scale(atmospherePressure, 1))
        .staticPressure(scale(staticPressure, 1))
        .dynamicPressure(scale(dynamicPressure, 1))
        .gasMeterGaugePressure(scale(gasMeterGaugePressure, 3))
        .moistureRatio(scale(moistureRatio, 2))
        .gasDensity(scale(gasDensity, 3))
        .oxygenCorrected(scale(oxygenCorrected, 1))
        .pointCount(pointCount)
        .build();
  }
  
  private static BigDecimal scale(BigDecimal value, int scale) {
    return value == null ? null : value.setScale(scale, RoundingMode.HALF_UP);
  }
}
