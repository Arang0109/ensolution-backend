package com.project.easywork.common.util.pressure.strategy;

public class MmH20ToPaStrategy implements PressureToPaStrategy{
  @Override
  public double toPa(double value) {
    return value * 9.80638;
  }
}
