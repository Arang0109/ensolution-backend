package com.project.easywork.common.util.measurePoint;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RectangleStrategy implements MeasurePointStrategy {
  
  private static final BigDecimal AREA_0_25 = new BigDecimal("0.25");
  private static final BigDecimal AREA_1 = new BigDecimal("1");
  private static final BigDecimal AREA_4 = new BigDecimal("4");
  private static final BigDecimal AREA_20 = new BigDecimal("20");
  
  @Override
  public Integer calculate(BigDecimal... values) {
    BigDecimal x = values[0];
    BigDecimal y = values[1];
    
    BigDecimal area = x.multiply(y).setScale(3, RoundingMode.HALF_UP);
    BigDecimal length = determineLength(area);
    
    if (area.compareTo(AREA_0_25) <= 0) return 1;
    if (area.compareTo(AREA_20) >= 0) return 20;
    
    int n = calculateAxisCount(x, length);
    int m = calculateAxisCount(y, length);
    
    return n * m;
  }
  
  private BigDecimal determineLength(BigDecimal area) {
    if (area.compareTo(AREA_1) <= 0) return new BigDecimal("0.5");
    if (area.compareTo(AREA_4) <= 0) return new BigDecimal("0.667");
    return new BigDecimal("1.0");   // area <= 20.0
  }
  
  private int calculateAxisCount(BigDecimal size, BigDecimal maxSpacing) {
    int count = 1;
    
    if (maxSpacing.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("maxSpacing must be greater than 0");
    }
    
    while (true) {
      BigDecimal spacing = size.divide(
          BigDecimal.valueOf(count),
          6,
          RoundingMode.HALF_UP
      );
      
      // spacing <= maxSpacing 이면 종료
      if (spacing.compareTo(maxSpacing) <= 0) {
        break;
      }
      
      count++;
    }
    
    return count;
  }
}
