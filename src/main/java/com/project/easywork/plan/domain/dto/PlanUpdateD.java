package com.project.easywork.plan.domain.dto;

import com.project.easywork.plan.domain.MeasurementField;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PlanUpdateD {
  private MeasurementField measurementField;
  private LocalDate measureDate;
  private String measurementType;
}
