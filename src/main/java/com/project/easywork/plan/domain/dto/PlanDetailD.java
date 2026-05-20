package com.project.easywork.plan.domain.dto;

import com.project.easywork.measurement.domain.document.MeasurementDoc;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PlanDetailD {
  private PlanD plan;
  private MeasurementDoc measurementInfo;
}