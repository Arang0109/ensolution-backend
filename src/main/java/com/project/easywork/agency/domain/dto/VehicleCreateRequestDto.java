package com.project.easywork.agency.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class VehicleCreateRequestDto {
  private Long teamId;
  @NotBlank(message = "필수 입력") private String vehicleNumber;
}