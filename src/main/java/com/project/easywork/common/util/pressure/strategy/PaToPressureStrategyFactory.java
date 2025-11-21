package com.project.easywork.common.util.pressure.strategy;

public class PaToPressureStrategyFactory {
  
  public static PaToPressureStrategy of(String unit) {
    if (unit == null) throw new IllegalArgumentException("unit cannot be null");
    
    return switch (unit.toLowerCase()) {
      case "hpa" -> pa -> pa / 100;
      case "pa" -> pa -> pa;
      case "mmhg" -> pa -> pa * 0.007500616;
      case "mmh2o", "mmh₂o" -> pa -> pa / 9.80665;
      default -> throw new IllegalArgumentException(unit + " is not supported");
    };
  }
}