package com.project.easywork.agency.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class VehicleUpdateRequestDto {
  private Long teamId;
  private String vehicleNumber;
}