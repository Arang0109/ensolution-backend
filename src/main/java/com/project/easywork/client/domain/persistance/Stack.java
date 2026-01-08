
package com.project.easywork.client.domain.persistance;

import com.project.easywork.client.domain.Grade;
import com.project.easywork.client.domain.Shape;
import com.project.easywork.client.domain.Orientation;
import com.project.easywork.client.domain.dto.stack.StackUpdateD;
import com.project.easywork.common.domain.BaseEntity;
import com.project.easywork.schedule.domain.persistance.Schedule;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Getter
@Table(
    name = "stack",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_workplace_name",
            columnNames = {"workplace_id", "name"}
        )
    }
)
public class Stack extends BaseEntity {
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "workplace_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  @ToString.Exclude
  private Workplace workplace;
  
  @Column(nullable = false, length = 100)
  private String name;
  
  @Column(name = "sems_number", nullable = false, length = 10)
  private String semsNumber;
  
  @Enumerated(EnumType.STRING)
  @Column(length = 10)
  private Grade grade;
  
  @Column(length = 10)
  private Double height;
  
  @Column(name ="horizontal_length")
  private Double horizontalLength;
  
  @Column(name ="vertical_length")
  private Double verticalLength;
  
  @Enumerated(EnumType.STRING)
  private Shape shape;
  
  @Enumerated(EnumType.STRING)
  private Orientation orientation;
  
  @Column(name = "standard_oxygen")
  private Double standardOxygen;
  
  @Column(columnDefinition = "LONGTEXT")
  private String remark;
  
  @OneToMany(mappedBy = "stack", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Prevention> preventions = new ArrayList<>();
  
  @OneToMany(mappedBy = "stack", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<StackMeasurement> stackMeasurements = new ArrayList<>();
  
  @OneToMany(mappedBy = "stack")
  private List<Schedule> schedules = new ArrayList<>();
  
  public void attachWorkplace(Workplace workplace) {
    this.workplace = workplace;
    workplace.getStacks().add(this);
  }
  
  public void update(StackUpdateD dto) {
    StackBuilder<?, ?> builder = this.toBuilder();
    
    Optional.ofNullable(dto.getName())
        .ifPresent(v -> this.name = v);
    
    Optional.ofNullable(dto.getSemsNumber())
        .ifPresent(v -> this.semsNumber = v);
    
    Optional.ofNullable(dto.getGrade())
        .ifPresent(v -> this.grade = v);
    
    Optional.ofNullable(dto.getHeight())
        .ifPresent(v -> this.height = v);
    
    Optional.ofNullable(dto.getHorizontalLength())
        .ifPresent(v -> this.horizontalLength = v);
    
    Optional.ofNullable(dto.getVerticalLength())
        .ifPresent(v -> this.verticalLength = v);
    
    Optional.ofNullable(dto.getShape())
        .ifPresent(v -> this.shape = v);
    
    Optional.ofNullable(dto.getOrientation())
        .ifPresent(v -> this.orientation = v);
    
    Optional.ofNullable(dto.getStandardOxygen())
        .ifPresent(v -> this.standardOxygen = v);
    
    Optional.ofNullable(dto.getRemark())
        .ifPresent(v -> this.remark = v);
    
    apply(builder.build());
  }
  
  private void apply(Stack stack) {
    this.name = stack.getName();
    this.semsNumber = stack.getSemsNumber();
    this.grade = stack.getGrade();
    this.height = stack.getHeight();
    this.horizontalLength = stack.getHorizontalLength();
    this.verticalLength = stack.getVerticalLength();
    this.shape = stack.getShape();
    this.orientation = stack.getOrientation();
    this.standardOxygen = stack.getStandardOxygen();
    this.remark = stack.getRemark();
  }
}