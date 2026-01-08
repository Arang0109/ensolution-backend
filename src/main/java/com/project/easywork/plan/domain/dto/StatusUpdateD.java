package com.project.easywork.plan.domain.dto;

import com.project.easywork.plan.domain.PlanStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class StatusUpdateD {
  private PlanStatus status;
}
