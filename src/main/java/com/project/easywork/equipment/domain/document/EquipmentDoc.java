package com.project.easywork.equipment.domain.document;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.project.easywork.equipment.domain.EquipStatus;
import com.project.easywork.equipment.domain.EquipType;
import com.project.easywork.equipment.domain.document.spec.*;
import com.project.easywork.equipment.domain.dto.EquipmentCreateReqD;
import com.project.easywork.equipment.domain.dto.EquipmentUpdateReqD;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

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
  
  private EquipStatus status;
  
  @JsonTypeInfo(
      use = JsonTypeInfo.Id.NAME,
      include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
      property = "type"
  )
  @JsonSubTypes({
      @JsonSubTypes.Type(value = ParticleSamplerSpec.class, name = "PARTICLE_SAMPLER"),
      @JsonSubTypes.Type(value = GasSamplerSpec.class, name = "GAS_SAMPLER"),
      @JsonSubTypes.Type(value = PitotTubeSpec.class, name = "PITOT_TUBE"),
      @JsonSubTypes.Type(value = NozzleSpec.class, name = "NOZZLE"),
      @JsonSubTypes.Type(value = OtherSpec.class, name = "OTHER")
  })
  private EquipmentSpec spec;
  
  public static EquipmentDoc createBase(EquipmentCreateReqD dto) {
    return EquipmentDoc.builder()
        .type(dto.type())
        .managementNumber(dto.managementNumber())
        .serialNumber(dto.serialNumber())
        .modelName(dto.modelName())
        .equipmentName(dto.equipmentName())
        .manufacturer(dto.manufacturer())
        .alias(dto.alias())
        .price(dto.price())
        .originCountry(dto.originCountry())
        .purchaseDate(dto.purchaseDate())
        .remark(dto.remark())
        .calibrationCycle(dto.calibrationCycle())
        .status(EquipStatus.ACTIVE)
        .build();
  }
  
  public void changeStatus(EquipStatus status) {
    if (this.status == EquipStatus.DELETED) {
      throw new IllegalStateException("삭제된 장비는 상태 변경 불가");
    }
    this.status = status;
  }
  
  public void updateSpec(EquipmentSpec spec) {
    if (spec == null) return;
    this.spec = spec;
  }
  
  public void updateCommonFields(EquipmentUpdateReqD dto) {
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
  }
  
}