
package com.project.easywork.client.domain.persistance;

import com.project.easywork.client.domain.dto.facility.FacilityUpdateD;
import com.project.easywork.client.domain.dto.prevention.PreventionUpdateD;
import com.project.easywork.client.domain.dto.target.TargetUpdateD;
import com.project.easywork.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Getter
@Table(name = "prevention")
public class Prevention extends BaseEntity {
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "stack_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Stack stack;
  
  @Column(nullable = false, length = 100)
  private String name;
  
  @Column(columnDefinition = "LONGTEXT")
  private String remark;
  
  @Builder.Default
  @OneToMany(mappedBy = "prevention", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Target> targets = new ArrayList<>();
  
  @Builder.Default
  @OneToMany(mappedBy = "prevention", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Facility> facilities = new ArrayList<>();
  
  public void attachStack(Stack stack) {
    this.stack = stack;
    stack.getPreventions().add(this);
  }
  
  public void update(PreventionUpdateD dto) {
    if (dto == null) return;
    
    if (dto.getName() != null) {
      this.name = dto.getName();
    }
    
    if (dto.getRemark() != null) {
      this.remark = dto.getRemark();
    }
  }
  
  public void updateFacilities(List<FacilityUpdateD> dtos) {
    if (dtos == null) return;
    
    Map<Long, Facility> existingMap =
        this.facilities.stream()
            .collect(Collectors.toMap(Facility::getId, Function.identity()));
    
    for (FacilityUpdateD dto : dtos) {
      if (dto.getId() == null) {
        Facility newFacility = Facility.create(dto, this);
        this.facilities.add(newFacility);
      } else {
        Facility facility = existingMap.remove(dto.getId());
        if (facility == null) {
          throw new IllegalArgumentException("존재하지 않는 배출시설");
        }
        facility.update(dto);
      }
    }
    
    existingMap.values().forEach(this::removeFacility);
  }
  
  public void updateTargets(List<TargetUpdateD> dtos) {
    if (dtos == null) return;
    
    Map<Long, Target> existingMap =
        this.targets.stream()
            .collect(Collectors.toMap(Target::getId, Function.identity()));
    
    for (TargetUpdateD dto : dtos) {
      if (dto.getId() == null) {
        Target newTarget = Target.create(dto, this);
        this.targets.add(newTarget);
      } else {
        Target target = existingMap.remove(dto.getId());
        if (target == null) {
          throw new IllegalArgumentException("존재하지 않는 제거대상물질");
        }
        target.update(dto);
      }
    }
    
    existingMap.values().forEach(this::removeTarget);
  }
  
  public void removeFacility(Facility facility) {
    this.facilities.remove(facility);
    facility.detachPrevention();
  }
  
  public void removeTarget(Target target) {
    this.targets.remove(target);
    target.detachPrevention();
  }
}