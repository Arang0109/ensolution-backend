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
  
  private boolean simplifiedMeasurement = true;
  private String vehicleNumber; // 차량 번호는 변경될 수 있기 때문에 따로 관리
  private String mentor;
  private String mentee;
  
  private String particleSamplerId;
  private String gasSamplerId;
  private String pitotTubeId;
  private String nozzleId;
}