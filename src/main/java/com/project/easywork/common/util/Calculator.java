package com.project.easywork.common.util;

import java.util.List;

public class Calculator {
  
  public static Double toActualDensity(Double gasVolume, Double temperature, Double pressure) {
    return gasVolume
        * (273.0 / (273.0 + temperature))   // 온도 보정
        * (pressure / 760.0);            // 압력 보정
  }
  
  public static Double avg(List<Double> values) {
    return values.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
  }
  
  public static Double calGasDensity(Double o2, Double co2, Double co, Double moisture) {
    
    double h2o = moisture;
    
    double n2 = 100.0 - (o2 + co2 + co + h2o);
    
    return (32.0/22.4)*(o2/100.0)
        + (44.0/22.4)*(co2/100.0)
        + (28.0/22.4)*(n2/100.0)
        + (18.0/22.4)*(h2o/100.0);
  }
}
