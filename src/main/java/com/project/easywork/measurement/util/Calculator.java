package com.project.easywork.measurement.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

@Component
public class Calculator {
  
  public BigDecimal round(BigDecimal value, int scale) {
    return value.setScale(scale, RoundingMode.HALF_UP);
  }
  
  public BigDecimal averageIgnoringNull(List<BigDecimal> values, int scale) {
    if (values == null) return BigDecimal.ZERO;
    
    List<BigDecimal> filtered = values.stream()
        .filter(Objects::nonNull)
        .toList();
    
    if (filtered.isEmpty()) return BigDecimal.ZERO;
    
    return filtered.stream()
        .reduce(BigDecimal.ZERO, BigDecimal::add)
        .divide(BigDecimal.valueOf(filtered.size()), scale, RoundingMode.HALF_UP);
  }
  
  public BigDecimal averageTreatNullAsZero(List<BigDecimal> values, int scale) {
    if (values == null || values.isEmpty()) return BigDecimal.ZERO;
    
    return values.stream()
        .map(v -> v == null ? BigDecimal.ZERO : v)
        .reduce(BigDecimal.ZERO, BigDecimal::add)
        .divide(BigDecimal.valueOf(values.size()), scale, RoundingMode.HALF_UP);
  }
}