package com.project.easywork.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class GasCalculator {
  
  private final static BigDecimal STD_AIR_VOLUME = BigDecimal.valueOf(22.4);
  
  public static BigDecimal toActualDensity(BigDecimal gasVolume, BigDecimal temp, BigDecimal pressure) {
    BigDecimal term1 = BigDecimal.valueOf(273)
        .divide(BigDecimal.valueOf(273).add(temp), 10, RoundingMode.HALF_UP);
    
    BigDecimal term2 = pressure
        .divide(BigDecimal.valueOf(760), 10, RoundingMode.HALF_UP);
    
    return gasVolume.multiply(term1).multiply(term2);
  }
  
  public static BigDecimal avg(List<Double> values) {
    if (values == null || values.isEmpty()) {
      return BigDecimal.ZERO;
    }
    
    BigDecimal sum = BigDecimal.ZERO;
    
    for (Double v : values) {
      if (v != null) {
        sum = sum.add(BigDecimal.valueOf(v));
      }
    }
    
    return sum.divide(BigDecimal.valueOf(values.size()), 10, RoundingMode.HALF_UP);
  }
  
  public static BigDecimal calGasDensity(BigDecimal o2, BigDecimal co2, BigDecimal co, BigDecimal moisture) {
    BigDecimal hundred = BigDecimal.valueOf(100);
    
    BigDecimal n2 = hundred.subtract(
        o2.add(co2).add(co).add(moisture)
    );
    
    BigDecimal result = BigDecimal.ZERO;
    
    result = result.add(
        BigDecimal.valueOf(32.0).divide(STD_AIR_VOLUME, 10, RoundingMode.HALF_UP)
            .multiply(o2.divide(hundred, 10, RoundingMode.HALF_UP))
    );
    
    result = result.add(
        BigDecimal.valueOf(44.0).divide(STD_AIR_VOLUME, 10, RoundingMode.HALF_UP)
            .multiply(co2.divide(hundred, 10, RoundingMode.HALF_UP))
    );
    
    result = result.add(
        BigDecimal.valueOf(28.0).divide(STD_AIR_VOLUME, 10, RoundingMode.HALF_UP)
            .multiply(n2.divide(hundred, 10, RoundingMode.HALF_UP))
    );
    
    result = result.add(
        BigDecimal.valueOf(18.0).divide(STD_AIR_VOLUME, 10, RoundingMode.HALF_UP)
            .multiply(moisture.divide(hundred, 10, RoundingMode.HALF_UP))
    );
    
    return result;
  }
}
