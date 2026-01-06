package com.project.easywork.agency.domain.entity;

import com.project.easywork.agency.domain.dto.TeamUpdateRequestDto;
import com.project.easywork.equipment.domain.persistance.Equipment;
import com.project.easywork.equipment.domain.persistance.PitotTube;
import com.project.easywork.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@Table(name = "team")
public class Team {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private Long id;
  
  @Column(nullable = false, length = 100)
  private String name;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "particular_equip_id")
  private Equipment particularEquip;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pitot_tube_id")
  private PitotTube pitotTube;
  
  @OneToMany(mappedBy = "team", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @ToString.Exclude
  private List<User> members = new ArrayList<>();
  
  @OneToMany(mappedBy = "team", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @ToString.Exclude
  private List<Vehicle> vehicles = new ArrayList<>();
  
  public void update(TeamUpdateRequestDto dto) {
    Optional.ofNullable(dto.getName())
        .filter(str -> !str.isBlank())
        .ifPresent(this::setName);
  }
}