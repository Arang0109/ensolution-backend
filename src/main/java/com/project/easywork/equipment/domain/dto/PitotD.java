package com.project.easywork.equipment.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class PitotD {
  private Long id;
  private String type;
  private String modelName;
  private String equipmentName;
}