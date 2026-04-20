package com.project.easywork.report.domain.stack;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TargetDataD {
  private String targetSubstance;
  private BigDecimal removalEfficiency;
}