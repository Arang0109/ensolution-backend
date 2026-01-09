package com.project.easywork.equipment.domain.persistance;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Getter
@Table(name = "pitot_tube_coefficient")
public class PitotTubeCoefficient {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private Long id;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pitot_tube_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private PitotTube pitotTube;
  
  @Column(precision = 4, scale = 2, nullable = false)
  private BigDecimal velocity;
  
  @Column(precision = 4, scale = 3, nullable = false)
  private BigDecimal coefficient;
}
