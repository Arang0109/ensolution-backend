package com.project.easywork.client.domain.persistance;

import com.project.easywork.client.domain.dto.prevention.PreventionUpdateRequestDto;
import com.project.easywork.client.domain.dto.target.TargetUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Optional;

@Entity
@Getter
@Setter
@Table(name = "target")
public class Target {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private Long id;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "prevention_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Prevention prevention;
  
  @Column(name = "target_substance", length = 100)
  private String targetSubstance;
  
  @Column(name = "removal_efficiency")
  private Double removalEfficiency;
  
  public void update(TargetUpdateRequestDto dto) {
    Optional.ofNullable(dto.getTargetSubstance())
        .filter(targetSubstance -> !targetSubstance.isBlank())
        .ifPresent(this::setTargetSubstance);
    
    Optional.ofNullable(dto.getRemovalEfficiency())
        .ifPresent(this::setRemovalEfficiency);
  }
}
