package com.project.easywork.common.util.pressure;

public class PressureConverter {
  
  public static Double convert(Double value, PressureUnit from, PressureUnit to) {
    if (value == null) return null;
    
    double pa = from.toPa(value);
    return to.fromPa(pa);
  }
  
  public static Double toMmHg(Double value, PressureUnit from) {
    return convert(value, from, PressureUnit.MMHG);
  }
}
