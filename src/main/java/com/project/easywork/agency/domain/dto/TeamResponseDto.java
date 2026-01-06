package com.project.easywork.agency.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TeamResponseDto {
  private Long id;
  private String name;
  private Long particularEquipId;
  private Long pitotTubeId;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
}