package com.project.easywork.agency.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TeamResponseDto {
  private Long id;
  private String name;
  private Long particularEquipId;
  private Long pitotTubeId;
}