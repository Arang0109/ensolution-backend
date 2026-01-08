package com.project.easywork.client.domain.persistance;

import com.project.easywork.client.domain.dto.target.TargetUpdateD;
import com.project.easywork.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Getter
@Table(name = "target")
public class Target extends BaseEntity {
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "prevention_id")
  private Prevention prevention;
  
  @Column(name = "target_substance", length = 100)
  private String targetSubstance;
  
  @Column(name = "removal_efficiency")
  private Double removalEfficiency;
  
  public static Target create(TargetUpdateD dto, Prevention prevention) {
    Target target = Target.builder()
        .targetSubstance(dto.getTargetSubstance())
        .removalEfficiency(dto.getRemovalEfficiency())
        .build();
    
    target.attachPrevention(prevention);
    return target;
  }
  
  public void attachPrevention(Prevention prevention) {
    this.prevention = prevention;
  }
  
  public void detachPrevention() {
    this.prevention = null;
  }
  
  public void update(TargetUpdateD dto) {
    if (dto.getTargetSubstance() != null) {
      this.targetSubstance = dto.getTargetSubstance();
    }
    if (dto.getRemovalEfficiency() != null) {
      this.removalEfficiency = dto.getRemovalEfficiency();
    }
  }
}
