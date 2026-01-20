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
@Table(name = "particular_sampler")
public class ParticularSampler {
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
  
  @Column(name = "orificeDP", precision = 10, scale = 4)
  private BigDecimal orificeDP;
  
  @Column(name = "YD", precision = 10, scale = 4)
  private BigDecimal yd;
  
  protected void setEquipment(Equipment equipment) {
    this.equipment = equipment;
  }
}