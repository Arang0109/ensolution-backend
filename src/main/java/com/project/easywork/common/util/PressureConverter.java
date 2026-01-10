package com.project.easywork.common.util;

import com.project.easywork.common.domain.PressureUnit;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PressureConverter {
  
  private static final int DEFAULT_SCALE = 3;
  
  public static BigDecimal convert(BigDecimal value, PressureUnit from, PressureUnit to) {
    if (value == null || from == null || to == null) {
      return null;
    }
    
    BigDecimal pa = from.toPa(value);
    BigDecimal converted = to.fromPa(pa);
    
    return normalize(converted, DEFAULT_SCALE);
  }
  
  public static BigDecimal toMmHg(BigDecimal value, PressureUnit from) {
    return convert(value, from, PressureUnit.MMHG);
  }
  
  public static BigDecimal toPa(BigDecimal value, PressureUnit from) {
    return convert(value, from, PressureUnit.PA);
  }
  
  public static BigDecimal toHpa(BigDecimal value, PressureUnit from) {
    return convert(value, from, PressureUnit.HPA);
  }
  
  private static BigDecimal normalize(BigDecimal value, int scale) {
    return value == null ? null : value.setScale(scale, RoundingMode.HALF_UP);
  }
}