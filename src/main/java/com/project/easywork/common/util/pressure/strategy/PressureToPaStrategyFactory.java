package com.project.easywork.common.util.pressure.strategy;

public class PressureToPaStrategyFactory {
  
  public static PressureToPaStrategy of(String unit) {
    if (unit == null) throw new IllegalArgumentException("유닛은 NULL이 될 수 없습니다.");
    
    return switch (unit.toLowerCase()) {
      case "hpa" -> new HpaToPaStrategy();
      case "pa" -> new PaToPaStrategy();
      case "mmhg" -> new MmHgToPaStrategy();
      case "mmh2o", "mmh₂o" -> new MmH20ToPaStrategy();
      default -> throw new IllegalArgumentException(unit + " : 지원하지 않는 유닛입니다.");
    };
  }
}