package com.project.easywork.measurement.util;

import java.math.BigDecimal;

public final class NumberUtils {
  private NumberUtils() {}
  
  public static BigDecimal zeroIfNull(BigDecimal value) {
    return value == null ? BigDecimal.ZERO : value;
  }
}