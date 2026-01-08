package com.project.easywork.client.domain.persistance;

import com.project.easywork.client.domain.dto.target.TargetUpdateD;
import com.project.easywork.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Optional;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Getter
@Table(name = "target")
public class Target extends BaseEntity {
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "prevention_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Prevention prevention;
  
  @Column(name = "target_substance", length = 100)
  private String targetSubstance;
  
  @Column(name = "removal_efficiency")
  private Double removalEfficiency;
  
  public void attachPrevention(Prevention prevention) {
    this.prevention = prevention;
    prevention.getTargets().add(this);
  }
  
  public void update(TargetUpdateD dto) {
    TargetBuilder<?, ?> builder = toBuilder();
    
    Optional.ofNullable(dto.getTargetSubstance())
        .ifPresent(v -> this.targetSubstance = v);
    
    Optional.ofNullable(dto.getRemovalEfficiency())
        .ifPresent(v -> this.removalEfficiency = v);
    
    apply(builder.build());
  }
  
  private void apply(Target target) {
    this.targetSubstance = target.getTargetSubstance();
    this.removalEfficiency = target.getRemovalEfficiency();
  }
}
