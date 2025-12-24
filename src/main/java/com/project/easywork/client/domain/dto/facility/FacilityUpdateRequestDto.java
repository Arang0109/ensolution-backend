package com.project.easywork.client.domain.dto.facility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FacilityUpdateRequestDto {
  private String name;
  private String fuelUsage;
  private String itemOutput;
  private String fuelInput;
  private String fuelType;
  private String remark;
}