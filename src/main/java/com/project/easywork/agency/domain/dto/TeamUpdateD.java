package com.project.easywork.agency.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TeamUpdateD {
  private String name;
  private String vehicleNumber;
  private String mentor;
  private String mentee;
  private String particleSamplerId;
  private String gasSamplerId;
  private String pitotTubeId;
  private String nozzleId;
}