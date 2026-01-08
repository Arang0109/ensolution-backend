
package com.project.easywork.client.domain.persistance;

import com.project.easywork.client.domain.dto.facility.FacilityUpdateD;
import com.project.easywork.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Optional;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Getter
@Table(name = "facility")
public class Facility extends BaseEntity {
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "prevention_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
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
  
  public void attachPrevention(Prevention prevention) {
    this.prevention = prevention;
    prevention.getFacilities().add(this);
  }
  
  public void update(FacilityUpdateD dto) {
    FacilityBuilder<?, ?> builder = toBuilder();
    
    Optional.ofNullable(dto.getName())
            .ifPresent(v -> this.name = v);
    
    Optional.ofNullable(dto.getFuelUsage())
        .ifPresent(v -> this.fuelUsage = v);
    
    Optional.ofNullable(dto.getItemOutput())
        .ifPresent(v -> this.itemOutput = v);
    
    Optional.ofNullable(dto.getFuelInput())
        .ifPresent(v -> this.fuelInput = v);
    
    Optional.ofNullable(dto.getFuelType())
        .ifPresent(v -> this.fuelType = v);
    
    apply(builder.build());
  }
  
  private void apply(Facility facility) {
    this.name = facility.getName();
    this.fuelUsage = facility.getFuelUsage();
    this.itemOutput = facility.getItemOutput();
    this.fuelInput = facility.getFuelInput();
    this.fuelType = facility.getFuelType();
    this.remark = facility.getRemark();
  }
}