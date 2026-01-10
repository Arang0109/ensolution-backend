package com.project.easywork.common.util.measurePoint;

import java.math.BigDecimal;

public interface MeasurePointStrategy {
  Integer calculate(BigDecimal...values);
}
