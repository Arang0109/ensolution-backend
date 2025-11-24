package com.project.easywork.agency.entity;

import com.project.easywork.auth.domain.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "team")
public class Team {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "team_id")
  private Long teamId;
  
  @Column(name = "team_name", nullable = false, length = 100)
  private String teamName;
  
  @OneToMany(mappedBy = "team", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @ToString.Exclude
  private List<User> users = new ArrayList<>();
  
  @OneToMany(mappedBy = "team", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @ToString.Exclude
  private List<Vehicle> vehicles = new ArrayList<>();
}