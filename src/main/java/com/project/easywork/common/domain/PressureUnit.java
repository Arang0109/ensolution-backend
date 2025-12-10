package com.project.easywork.common.domain;

import java.util.function.Function;

public enum PressureUnit {
  PA(v -> v, v -> v),
  HPA(v -> v * 100, v -> v / 100),
  KPA(v -> v * 1000, v -> v / 1000),
  ATM(v -> v * 101325, v -> v / 101325),
  MMHG(v -> v * 133.322, v -> v / 133.322),
  MMH2O(v -> v * 9.80665, v -> v / 9.80665),
  PSI(v -> v * 6894.757, v -> v / 6894.757);
  
  private final Function<Double, Double> toPa;
  private final Function<Double, Double> fromPa;
  
  PressureUnit(Function<Double, Double> toPa, Function<Double, Double> fromPa) {
    this.toPa = toPa;
    this.fromPa = fromPa;
  }
  
  public double toPa(double value) { return toPa.apply(value); }
  public double fromPa(double pa) { return fromPa.apply(pa); }
  
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

