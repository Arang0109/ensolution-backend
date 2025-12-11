package com.project.easywork.equipment.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class EquipmentCalibrationUpdateReqDto {
  
  private LocalDate calibrationDate;
  private String agency;
  private BigDecimal cost;
  private LocalDate nextCalibrationDate;
  private String certificateUrl;
  private String remark;
}
