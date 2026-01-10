package com.project.easywork.common.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;

public enum PressureUnit {
  
  PA(v -> v, v -> v),
  
  HPA(
      v -> v.multiply(BigDecimal.valueOf(100)),
      v -> v.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
  ),
  
  KPA(
      v -> v.multiply(BigDecimal.valueOf(1000)),
      v -> v.divide(BigDecimal.valueOf(1000), 10, RoundingMode.HALF_UP)
  ),
  
  ATM(
      v -> v.multiply(BigDecimal.valueOf(101325)),
      v -> v.divide(BigDecimal.valueOf(101325), 10, RoundingMode.HALF_UP)
  ),
  
  MMHG(
      v -> v.multiply(new BigDecimal("133.322")),
      v -> v.divide(new BigDecimal("133.322"), 10, RoundingMode.HALF_UP)
  ),
  
  MMH2O(
      v -> v.multiply(new BigDecimal("9.80665")),
      v -> v.divide(new BigDecimal("9.80665"), 10, RoundingMode.HALF_UP)
  ),
  
  PSI(
      v -> v.multiply(new BigDecimal("6894.757")),
      v -> v.divide(new BigDecimal("6894.757"), 10, RoundingMode.HALF_UP)
  );
  
  private final Function<BigDecimal, BigDecimal> toPa;
  private final Function<BigDecimal, BigDecimal> fromPa;
  
  PressureUnit(Function<BigDecimal, BigDecimal> toPa,
               Function<BigDecimal, BigDecimal> fromPa) {
    this.toPa = toPa;
    this.fromPa = fromPa;
  }
  
  public BigDecimal toPa(BigDecimal value) {
    if (value == null) return null;
    return toPa.apply(value);
  }
  
  public BigDecimal fromPa(BigDecimal pa) {
    if (pa == null) return null;
    return fromPa.apply(pa);
  }
  
  public static PressureUnit from(String unit) {
    if (unit == null) {
      throw new IllegalArgumentException("Pressure unit cannot be null");
    }
    
    return switch (unit.trim().toLowerCase()) {
      case "pa" -> PA;
      case "hpa" -> HPA;
      case "kpa" -> KPA;
      case "atm" -> ATM;
      case "mmhg" -> MMHG;
      case "mmh2o" -> MMH2O;
      case "psi" -> PSI;
      default -> throw new IllegalArgumentException("Unknown pressure unit: " + unit);
    };
  }
}