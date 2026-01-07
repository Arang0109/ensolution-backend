package com.project.easywork.equipment.domain.dto;

import com.project.easywork.equipment.domain.EquipType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class EquipmentResDto {
  @Schema(
      description = "장비 ID (PK)",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;
  
  private String managementNumber;
  private String serialNumber;
  private String modelName;
  private String equipmentName;
  private EquipType type;
  private BigDecimal price;
  private String manufacturer;
  private String originCountry;
  private LocalDate purchaseDate;
  private Boolean isAvailable;
  private LocalDate calibrationDate;
  private Integer calibrationCycle;
  private Double dh;
  private Double yd;
  
  private String remark;
}
