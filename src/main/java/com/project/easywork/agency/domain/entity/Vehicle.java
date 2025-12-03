package com.project.easywork.agency.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
}
