package com.project.easywork.measurement.dto.document.input;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Getter
@Builder(toBuilder = true)
public class ExhaustGasDoc {
  private List<BigDecimal> o2Concentration;
  private List<BigDecimal> co2Concentration;
  private List<BigDecimal> coConcentration;
  private List<BigDecimal> noxConcentration;
  private List<BigDecimal> soxConcentration;
  
  public ExhaustGasDoc normalize() {
    return this.toBuilder()
        .o2Concentration(scale(o2Concentration))
        .co2Concentration(scale(co2Concentration))
        .coConcentration(scale(coConcentration))
        .noxConcentration(scale(noxConcentration))
        .soxConcentration(scale(soxConcentration))
        .build();
  }
  
  public void merge(ExhaustGasDoc source) {
    if (source == null) return;
    
    if (source.o2Concentration != null)
      this.o2Concentration = source.o2Concentration;
    
    if (source.co2Concentration != null)
      this.co2Concentration = source.co2Concentration;
    
    if (source.coConcentration != null)
      this.coConcentration = source.coConcentration;
    
    if (source.noxConcentration != null)
      this.noxConcentration = source.noxConcentration;
    
    if (source.soxConcentration != null)
      this.soxConcentration = source.soxConcentration;
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