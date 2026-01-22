package com.project.easywork.equipment.domain.document;

import com.project.easywork.equipment.domain.EquipType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document("equipments")
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDoc {
  @Id
  private String id;
  
  private EquipType type;
  
  private String managementNumber;
  private String serialNumber;
  private String modelName;
  private String equipmentName;
  private String alias;
  
  private BigDecimal price;
  private String manufacturer;
  private String originCountry;
  private LocalDate purchaseDate;
  private String remark;
  
  private Integer calibrationCycle;
  private LocalDate lastCalibrationDate;
  
  private Object spec;
}