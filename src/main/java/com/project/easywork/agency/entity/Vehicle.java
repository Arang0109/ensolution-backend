package com.project.easywork.agency.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "vehicle")
public class Vehicle {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "vehicle_id")
  private Long vehicleId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "team_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  @ToString.Exclude
  private Team team;
  
  @Column(name = "vehicle_number", nullable = false, length = 100)
  private String vehicleNumber;
}
