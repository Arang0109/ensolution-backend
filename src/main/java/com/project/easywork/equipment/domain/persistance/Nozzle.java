package com.project.easywork.equipment.domain.persistance;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Getter
@Table(name = "nozzle")
public class Nozzle {
  @Id
  @Column(name = "equipment_id")
  private Long id;
  
  @MapsId
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "equipment_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Equipment equipment;
  
  @Column(nullable = false, length = 100)
  private String alias;
  
  @OneToMany(mappedBy = "nozzle", cascade = CascadeType.ALL)
  private List<NozzleSize> nozzleSizeList = new ArrayList<>();
}
