package com.project.easywork.equipment.domain.persistance;

import com.project.easywork.equipment.domain.PitotType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "pitot_tube")
public class PitotTube {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private Long id;
  
  @Enumerated(EnumType.STRING)
  private PitotType type;
  
  @Column(name = "model_name", length = 100)
  private String modelName;
  
  @Column(name = "equipment_name", length = 100)
  private String equipmentName;
  
  @OneToMany(mappedBy = "pitotTube", cascade = CascadeType.ALL)
  private List<PitotTubeCoefficient> pitotTubeCoefficientList = new ArrayList<>();
}
