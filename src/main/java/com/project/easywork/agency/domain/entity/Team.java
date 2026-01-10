package com.project.easywork.agency.domain.entity;

import com.project.easywork.equipment.domain.EquipType;
import com.project.easywork.equipment.domain.persistance.Equipment;
import com.project.easywork.equipment.domain.persistance.PitotTube;
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
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "particular_equip_id")
  private Equipment particularEquip;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pitot_tube_id")
  private PitotTube pitotTube;
  
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
   * Relation Logic
   * ========================= */
  private void attachParticularEquip(Equipment equipment) {
    if (equipment == null) {
      this.particularEquip = null;
      return;
    }
    
    if (!equipment.getType().equals(EquipType.PARTICULAR)) return;
    
    this.particularEquip = equipment;
  }
  
  private void attachPitotTube(PitotTube pitotTube) {
    if (pitotTube == null) {
      this.pitotTube = null;
      return;
    }
    
    this.pitotTube = pitotTube;
  }
  
  public void changeParticularEquip(Equipment equipment) {
    attachParticularEquip(equipment);
  }
  
  public void changePitotTube(PitotTube pitotTube) {
    attachPitotTube(pitotTube);
  }
  
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