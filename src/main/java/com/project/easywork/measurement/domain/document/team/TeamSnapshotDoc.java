package com.project.easywork.measurement.domain.document.team;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TeamSnapshotDoc {
  private Long teamId;
  private String teamName; // 측정팀
  private String vehicleNumber; // 측정차량
  private String mentor; // 사수
  private String mentee; // 부사수
}
