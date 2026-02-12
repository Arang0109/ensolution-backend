package com.project.easywork.agency.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TeamD {
  private Long id;
  private String name;
  private String vehicleNumber;
  private String mentor;
  private String mentee;
  private String particleSamplerId;
  private String gasSamplerId;
  private String pitotTubeId;
  private String nozzleId;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
}