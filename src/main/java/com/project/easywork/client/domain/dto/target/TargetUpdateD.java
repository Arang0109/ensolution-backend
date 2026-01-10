package com.project.easywork.client.domain.dto.target;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TargetUpdateD {
  private Long id;
  private String targetSubstance;
  private BigDecimal removalEfficiency;
}
