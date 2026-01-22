package com.project.easywork.agency.domain.entity;

import com.project.easywork.agency.domain.dto.TeamUpdateD;
import com.project.easywork.common.domain.BaseEntity;
import com.project.easywork.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Getter
@Table(name = "team")
public class Team extends BaseEntity {
  
  /* =========================
   * Relations
   * ========================= */
  
  @OneToMany(mappedBy = "team", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @ToString.Exclude
  private List<User> members = new ArrayList<>();
  
  /* =========================
   * Columns
   * ========================= */
  @Column(nullable = false, length = 100)
  private String name;
  
  @Column(name = "vehicle_number", length = 100)
  private String vehicleNumber;
  
  /* =========================
   * Team Logic
   * ========================= */
  public void update(TeamUpdateD dto) {
    if (dto.getName() != null && !dto.getName().isBlank()) {
      this.name = dto.getName();
    }
    
    if (dto.getVehicleNumber() != null && !dto.getVehicleNumber().isBlank()) {
      this.vehicleNumber = dto.getVehicleNumber();
    }
  }
}