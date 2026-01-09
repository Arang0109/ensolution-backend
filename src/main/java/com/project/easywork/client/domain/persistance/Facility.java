
package com.project.easywork.client.domain.persistance;

import com.project.easywork.client.domain.dto.facility.FacilityUpdateD;
import com.project.easywork.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Getter
@Table(name = "facility")
public class Facility extends BaseEntity {
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "prevention_id")
  private Prevention prevention;
  
  @Column(nullable = false, length = 100)
  private String name;
  
  @Column(name = "fuel_usage", length = 10)
  private String fuelUsage;
  
  @Column(name = "item_output", length = 10)
  private String itemOutput;
  
  @Column(name = "fuel_input", length = 10)
  private String fuelInput;
  
  @Column(name = "fuel_type", length = 20)
  private String fuelType;
  
  @Column(columnDefinition = "LONGTEXT")
  private String remark;
  
  public static Facility create(FacilityUpdateD dto, Prevention prevention) {
    Facility facility = Facility.builder()
        .name(dto.getName())                // 필수
        .fuelUsage(dto.getFuelUsage())
        .itemOutput(dto.getItemOutput())
        .fuelInput(dto.getFuelInput())
        .fuelType(dto.getFuelType())
        .remark(dto.getRemark())
        .build();
    
    facility.attachPrevention(prevention);
    return facility;
  }
  
  public void attachPrevention(Prevention prevention) {
    this.prevention = prevention;
  }
  
  public void detachPrevention() {
    this.prevention = null;
  }
  
  public void update(FacilityUpdateD dto) {
    if (dto.getName() != null) {
      this.name = dto.getName();
    }
    if (dto.getFuelUsage() != null) {
      this.fuelUsage = dto.getFuelUsage();
    }
    if (dto.getItemOutput() != null) {
      this.itemOutput = dto.getItemOutput();
    }
    if (dto.getFuelInput() != null) {
      this.fuelInput = dto.getFuelInput();
    }
    if (dto.getFuelType() != null) {
      this.fuelType = dto.getFuelType();
    }
    if (dto.getRemark() != null) {
      this.remark = dto.getRemark();
    }
  }
}