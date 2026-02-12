package com.project.easywork.plan.domain.dto;

import com.project.easywork.plan.domain.MeasureField;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PlanUpdateD {
  private Long stackId;
  private Long teamId;
  private MeasureField measureField;
  private LocalDate measureDate;
  private String measurementType;
}
