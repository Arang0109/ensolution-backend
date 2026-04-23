package com.project.easywork.measurement.util;

import com.project.easywork.common.exception.CustomException;
import com.project.easywork.common.exception.ErrorCode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class Calculator {
  
  // null -> 예외 던짐
  public BigDecimal round(BigDecimal value, int scale) {
    if (value == null) {
      throw new CustomException(ErrorCode.VALUE_MUST_NOT_BE_NULL);
    }
    return value.setScale(scale, RoundingMode.HALF_UP);
  }
  
  // null -> 0 리턴
  public BigDecimal averageTreatNullAsZero(List<BigDecimal> values, int scale) {
    if (values == null || values.isEmpty()) return BigDecimal.ZERO;
    
    return values.stream()
        .map(v -> v == null ? BigDecimal.ZERO : v)
        .reduce(BigDecimal.ZERO, BigDecimal::add)
        .divide(BigDecimal.valueOf(values.size()), scale, RoundingMode.HALF_UP);
  }
}