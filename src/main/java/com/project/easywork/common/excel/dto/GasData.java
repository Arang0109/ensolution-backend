package com.project.easywork.common.excel.dto;

import lombok.*;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GasData {
  private double standardOxygen = 20.9;
  private double oxygenCorrectionFactor = 1.0;
  private List<Double> o2 = Arrays.asList(20.9, 20.9, 20.9);
  private List<Double> co2 = Arrays.asList(0.0, 0.0, 0.0);
  private List<Double> co = Arrays.asList(0.0, 0.0, 0.0);
  
  public void calculateOxygenCorrectionFactor() {
    double avgO2 = this.o2.stream()
        .mapToDouble(Double::doubleValue)
        .average()
        .orElse(0.0);
    
    // 산소보정계수 계산식 적용
    this.oxygenCorrectionFactor = (21.0 - this.standardOxygen) / (21.0 - avgO2);
  }
}