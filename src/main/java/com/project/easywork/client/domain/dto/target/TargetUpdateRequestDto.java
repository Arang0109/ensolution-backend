package com.project.easywork.client.domain.dto.target;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class TargetUpdateRequestDto {
  private String targetSubstance;
  private Double removalEfficiency;
}
