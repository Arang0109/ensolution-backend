package com.project.easywork.equipment.domain.dto;

import com.project.easywork.equipment.domain.EquipType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class EquipUpdateD {
  private String managementNumber;
  private String serialNumber;
  private String modelName;
  private String equipmentName;
  private EquipType type;
  private BigDecimal price;
  private String manufacturer;
  private String originCountry;
  private LocalDate purchaseDate;
  private LocalDate calibrationDate;
  private Integer calibrationCycle;
  private Double dh;
  private Double yd;
  private String remark;
}
