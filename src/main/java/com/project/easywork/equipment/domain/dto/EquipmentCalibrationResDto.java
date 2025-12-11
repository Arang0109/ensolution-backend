package com.project.easywork.equipment.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class EquipmentCalibrationResDto {
  @Schema(
      description = "장비교정 ID (PK)",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;
  
  private Long equipmentId;
  private LocalDate calibrationDate;
  private String agency;
  private BigDecimal cost;
  private LocalDate nextCalibrationDate;
  private String certificateUrl;
  
  private String remark;
}
