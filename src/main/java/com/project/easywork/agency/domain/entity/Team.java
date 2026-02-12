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
  
  @Column(name = "mentor", length = 100)
  private String mentor;
  
  @Column(name = "mentee", length = 100)
  private String mentee;
  
  @Column(name = "particle_sampler_id")
  private String particleSamplerId;
  
  @Column(name = "gas_sampler_id")
  private String gasSamplerId;
  
  @Column(name = "pitot_tube_id")
  private String pitotTubeId;
  
  @Column(name = "nozzle_id")
  private String nozzleId;
  
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
    
    if (dto.getParticleSamplerId() != null && !dto.getParticleSamplerId().isBlank()) {
      this.particleSamplerId = dto.getParticleSamplerId();
    }
    
    if (dto.getGasSamplerId() != null && !dto.getGasSamplerId().isBlank()) {
      this.gasSamplerId = dto.getGasSamplerId();
    }
    
    if (dto.getPitotTubeId() != null && !dto.getPitotTubeId().isBlank()) {
      this.pitotTubeId = dto.getPitotTubeId();
    }
    
    if (dto.getNozzleId() != null && !dto.getNozzleId().isBlank()) {
      this.nozzleId = dto.getNozzleId();
    }
  }
}