package com.project.easywork.plan.domain.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PlanUpdateD {
  private Long stackId;
  private Long teamId;
  private LocalDate measureDate;
  private String measurementType;
}
