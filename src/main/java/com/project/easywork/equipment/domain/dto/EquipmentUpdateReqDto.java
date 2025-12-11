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
public class EquipmentUpdateReqDto {
  private String managementNumber;
  private String serialNumber;
  private String modelName;
  private String equipmentName;
  private BigDecimal price;
  private String manufacturer;
  private String originCountry;
  private LocalDate purchaseDate;
  private Integer calibrationCycle;
  private String remark;
}
