package com.project.easywork.client.domain.dto.target;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TargetUpdateRequestDto {
  private String targetSubstance;
  private Double removalEfficiency;
}
