package com.project.easywork.plan.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MeasurementCategory {
  OTHER("가스상"),
  HEAVY_METAL("중금속"),
  DUST("먼지"),
  MERCURY("수은");
  
  private final String description;
}
