package com.project.easywork.measurement.dto.document.input;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Getter
@Builder(toBuilder = true)
public class ExhaustGasDocument {
  private List<BigDecimal> o2Concentration;
  private List<BigDecimal> co2Concentration;
  private List<BigDecimal> coConcentration;
  private List<BigDecimal> noxConcentration;
  private List<BigDecimal> soxConcentration;
  
  public ExhaustGasDocument normalize() {
    return this.toBuilder()
        .o2Concentration(scale(o2Concentration))
        .co2Concentration(scale(co2Concentration))
        .coConcentration(scale(coConcentration))
        .noxConcentration(scale(noxConcentration))
        .soxConcentration(scale(soxConcentration))
        .build();
  }
  
  private static List<BigDecimal> scale(List<BigDecimal> value) {
    if (value == null) {
      return null;
    }
    
    return value.stream().map(
            v -> v == null ? null : v.setScale(1, RoundingMode.HALF_UP))
        .toList();
  }
}