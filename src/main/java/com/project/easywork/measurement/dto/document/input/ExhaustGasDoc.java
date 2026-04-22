package com.project.easywork.measurement.dto.document.input;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.List;

@Getter
@Builder(toBuilder = true)
public class ExhaustGasDoc {
  private List<BigDecimal> o2Concentration;
  private List<BigDecimal> co2Concentration;
  private List<BigDecimal> coConcentration;
  private List<BigDecimal> noxConcentration;
  private List<BigDecimal> soxConcentration;
  
  private BigDecimal gasDensity;
  private BigDecimal o2CorrectionFactor;
  
  private LocalTime gasAnalyzerStartTime;
  private LocalTime thcAnalyzerStartTime;
  
  public ExhaustGasDoc normalize() {
    return this.toBuilder()
        .o2Concentration(scale(o2Concentration))
        .co2Concentration(scale(co2Concentration))
        .coConcentration(scale(coConcentration))
        .noxConcentration(scale(noxConcentration))
        .soxConcentration(scale(soxConcentration))
        .build();
  }
  
  public ExhaustGasDoc merge(ExhaustGasDoc doc) {
    if (doc == null) return this;
    
    return this.toBuilder()
        .o2Concentration(
            doc.o2Concentration != null ? doc.o2Concentration : this.o2Concentration
        )
        .co2Concentration(
            doc.co2Concentration != null ? doc.co2Concentration : this.co2Concentration
        )
        .coConcentration(
            doc.coConcentration != null ? doc.coConcentration : this.coConcentration
        )
        .noxConcentration(
            doc.noxConcentration != null ? doc.noxConcentration : this.noxConcentration
        )
        .soxConcentration(
            doc.soxConcentration != null ? doc.soxConcentration : this.soxConcentration
        )
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