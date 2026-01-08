package com.project.easywork.client.domain.dto.facility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FacilityUpdateD {
  private Long id;
  private String name;
  private String fuelUsage;
  private String itemOutput;
  private String fuelInput;
  private String fuelType;
  private String remark;
}