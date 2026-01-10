package com.project.easywork.common.util.measurePoint;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CircleStrategy implements MeasurePointStrategy {
  
  private static final BigDecimal PI = new BigDecimal("3.141592653589793");
  private static final BigDecimal AREA_0_25 = new BigDecimal("0.25");
  
  private static final BigDecimal D_1_0 = new BigDecimal("1.0");
  private static final BigDecimal D_2_0 = new BigDecimal("2.0");
  private static final BigDecimal D_4_0 = new BigDecimal("4.0");
  private static final BigDecimal D_4_5 = new BigDecimal("4.5");
  
  @Override
  public Integer calculate(BigDecimal...values) {
    BigDecimal diameter = values[0];
    
    BigDecimal radius = diameter
        .divide(BigDecimal.valueOf(2),3, RoundingMode.HALF_UP);
    BigDecimal area = radius.multiply(radius).multiply(PI)
        .setScale(3, RoundingMode.HALF_UP);
    
    if (area.compareTo(AREA_0_25) <= 0) return 1;
    if (diameter.compareTo(D_1_0) <= 0) return 4;
    if (diameter.compareTo(D_2_0) <= 0) return 8;
    if (diameter.compareTo(D_4_0) <= 0) return 12;
    if (diameter.compareTo(D_4_5) <= 0) return 16;
    
    return 20;
  }
}
