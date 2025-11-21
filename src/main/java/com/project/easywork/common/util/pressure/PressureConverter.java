package com.project.easywork.common.util.pressure;

import com.project.easywork.common.util.pressure.strategy.*;

public class PressureConverter {
  
  public static Double toMmHg(Double value, String unit) {
    PressureToPaStrategy pressureToPa = PressureToPaStrategyFactory.of(unit);
    PaToPressureStrategy paToPressure = PaToPressureStrategyFactory.of("mmHg");
    
    return paToPressure.toPressure(pressureToPa.toPa(value));
  }
  
  public static Double convert(Double value, String fromUnit, String toUnit) {
    PressureToPaStrategy pressureToPa = PressureToPaStrategyFactory.of(fromUnit);
    PaToPressureStrategy paToPressure = PaToPressureStrategyFactory.of(toUnit);
    
    return paToPressure.toPressure(pressureToPa.toPa(value));
  }
}
