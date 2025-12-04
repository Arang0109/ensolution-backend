package com.project.easywork.agency.domain.entity;

import com.project.easywork.agency.domain.dto.VehicleUpdateRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Optional;

@Entity
@Getter
@Setter
@Table(name = "vehicle")
public class Vehicle {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private Long id;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "team_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  @ToString.Exclude
  private Team team;
  
  @Column(name = "vehicle_number", nullable = false, length = 100)
  private String vehicleNumber;
  
  public void update(Team team, VehicleUpdateRequestDto dto) {
    Optional.ofNullable(team)
        .ifPresent(this::setTeam);
    
    Optional.ofNullable(dto.getVehicleNumber())
        .filter(str -> !str.isBlank())
        .ifPresent(this::setVehicleNumber);
  }
}
