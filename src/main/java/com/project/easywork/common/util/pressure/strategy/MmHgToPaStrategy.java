package com.project.easywork.common.util.pressure.strategy;

public class MmHgToPaStrategy implements PressureToPaStrategy{
  @Override
  public double toPa(double value) {
    return value * 133.322387415;
  }
}
