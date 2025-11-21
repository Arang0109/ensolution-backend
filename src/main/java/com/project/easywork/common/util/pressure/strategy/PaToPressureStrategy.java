package com.project.easywork.common.util.pressure.strategy;

// 대기압은 소수점 한자리까지 표기

public interface PaToPressureStrategy {
  double toPressure(double value);
}
