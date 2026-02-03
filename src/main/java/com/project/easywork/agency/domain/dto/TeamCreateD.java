package com.project.easywork.agency.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TeamCreateD {
  @NotBlank(message = "필수 입력")  private String name;
  private String vehicleNumber;
  private String particleSamplerId;
  private String gasSamplerId;
  private String pitotTubeId;
  private String nozzleId;
}