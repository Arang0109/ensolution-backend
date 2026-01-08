package com.project.easywork.plan.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PlanCreateBundleD {
  private PlanCreateD plan;
  
  private Long companyId;
  private Long workplaceId;
  
  private String vehicleNumber;
  private Long seniorUserId;
  private Long juniorUserId;
  
  private Long equipmentId;
  private Long pitotTubeId;
}