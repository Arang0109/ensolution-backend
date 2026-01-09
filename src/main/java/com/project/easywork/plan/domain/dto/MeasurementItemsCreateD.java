package com.project.easywork.plan.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class MeasurementItemsCreateD {
  private Long planId;
  private Long stackMeasurementId;
}