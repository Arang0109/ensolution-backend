package com.project.easywork.equipment.domain.document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.easywork.equipment.domain.EquipType;
import com.project.easywork.equipment.domain.dto.EquipmentUpdateReqD;
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
  
  public void update(EquipmentUpdateReqD dto, ObjectMapper objectMapper) {
    
    if (dto.type() != null) {
      this.type = dto.type();
    }
    
    if (dto.managementNumber() != null) {
      this.managementNumber = dto.managementNumber();
    }
    
    if (dto.serialNumber() != null) {
      this.serialNumber = dto.serialNumber();
    }
    
    if (dto.modelName() != null) {
      this.modelName = dto.modelName();
    }
    
    if (dto.equipmentName() != null) {
      this.equipmentName = dto.equipmentName();
    }
    
    if (dto.alias() != null) {
      this.alias = dto.alias();
    }
    
    if (dto.price() != null) {
      this.price = dto.price();
    }
    
    if (dto.manufacturer() != null) {
      this.manufacturer = dto.manufacturer();
    }
    
    if (dto.originCountry() != null) {
      this.originCountry = dto.originCountry();
    }
    
    if (dto.purchaseDate() != null) {
      this.purchaseDate = dto.purchaseDate();
    }
    
    if (dto.remark() != null) {
      this.remark = dto.remark();
    }
    
    if (dto.calibrationCycle() != null) {
      this.calibrationCycle = dto.calibrationCycle();
    }
    
    if (dto.spec() != null) {
      this.spec = objectMapper.convertValue(dto.spec(), this.spec.getClass());
    }
  }
  
}