package com.project.easywork.measurement.dto.document.input;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ExhaustGasDocument {
  
  private DynamicPressureDocument dynamicPressure;
  private StaticPressureDocument staticPressure;
  
  private Double gasTemperature;
  private Double standardOxygen;
  
  private List<Double> o2Concentration;
  private List<Double> co2Concentration;
  private List<Double> coConcentration;
  private List<Double> noxConcentration;
  private List<Double> soxConcentration;
  
  // ------------------------
  // 동압
  // ------------------------
  @Getter
  @Builder
  public static class DynamicPressureDocument {
    private Double pressure;
    private String unit;
  }
  
  // ------------------------
  // 정압
  // ------------------------
  @Getter
  @Builder
  public static class StaticPressureDocument {
    private Double pressure;
    private String unit;
  }
}