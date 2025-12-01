
package com.project.easywork.client.domain.persistance;

import com.project.easywork.client.domain.dto.facility.FacilityUpdateRequestDto;
import com.project.easywork.client.domain.dto.prevention.PreventionUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Optional;

@Entity
@Getter
@Setter
@Table(name = "facility")
public class Facility {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private Long id;
  
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
  
  @CreationTimestamp
  @Column(name = "created_at", nullable = false)
  private LocalDate createdAt;
  
  @UpdateTimestamp
  @Column(name = "modified_at", nullable = false)
  private LocalDate modifiedAt;
  
  public void update(FacilityUpdateRequestDto dto) {
    Optional.ofNullable(dto.getName())
        .filter(name -> !name.isBlank())
        .ifPresent(this::setName);
    
    Optional.ofNullable(dto.getFuelUsage())
        .filter(str -> !str.isBlank())
        .ifPresent(this::setFuelUsage);
    
    Optional.ofNullable(dto.getItemOutput())
        .filter(str -> !str.isBlank())
        .ifPresent(this::setItemOutput);
    
    Optional.ofNullable(dto.getFuelInput())
        .filter(str -> !str.isBlank())
        .ifPresent(this::setFuelInput);
    
    Optional.ofNullable(dto.getFuelType())
        .filter(str -> !str.isBlank())
        .ifPresent(this::setFuelType);
    
    Optional.ofNullable(dto.getRemark())
        .ifPresent(this::setRemark);
  }
}