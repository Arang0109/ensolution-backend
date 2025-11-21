package com.project.easywork.common.util.pressure.strategy;

public class HpaToPaStrategy implements PressureToPaStrategy{
  @Override
  public double toPa(double value) {
    return value * 100 ;
  }
}
