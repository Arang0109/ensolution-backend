package com.project.easywork.report.domain.stack;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FacilityDataD {
  private String fuelUsage;
  private String itemOutput;
  private String fuelInput;
  private String fuelType;
}