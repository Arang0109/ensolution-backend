
package com.project.easywork.client.domain.persistance;

import com.project.easywork.client.domain.Grade;
import com.project.easywork.client.domain.Shape;
import com.project.easywork.client.domain.Orientation;
import com.project.easywork.client.domain.dto.stack.StackUpdateD;
import com.project.easywork.common.domain.BaseEntity;
import com.project.easywork.plan.domain.persistance.Plan;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
  @ToString.Exclude
  private Workplace workplace;
  
  @Column(nullable = false, length = 100)
  private String name;
  
  @Column(name = "sems_number", length = 10)
  private String semsNumber;
  
  @Enumerated(EnumType.STRING)
  @Column(length = 10)
  private Grade grade;
  
  @Column(precision = 10, scale = 1)
  private BigDecimal height;
  
  @Column(name = "horizontal_length", precision = 10, scale = 3)
  private BigDecimal horizontalLength;
  
  @Column(name = "vertical_length", precision = 10, scale = 3)
  private BigDecimal verticalLength;
  
  @Enumerated(EnumType.STRING)
  private Shape shape;
  
  @Enumerated(EnumType.STRING)
  private Orientation orientation;
  
  @Column(name = "standard_oxygen", precision = 5, scale = 1)
  private BigDecimal standardOxygen;
  
  @Column(columnDefinition = "LONGTEXT")
  private String remark;
  
  @OneToMany(mappedBy = "stack", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Prevention> preventions = new ArrayList<>();
  
  @OneToMany(mappedBy = "stack", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<StackMeasurement> stackMeasurements = new ArrayList<>();
  
  @OneToMany(mappedBy = "stack")
  private List<Plan> plans = new ArrayList<>();
  
  public void attachWorkplace(Workplace workplace) {
    this.workplace = workplace;
    workplace.getStacks().add(this);
  }
  
  public void update(StackUpdateD dto) {
    if (dto.getName() != null) {
      this.name = dto.getName();
    }
    if (dto.getSemsNumber() != null) {
      this.semsNumber = dto.getSemsNumber();
    }
    if (dto.getGrade() != null) {
      this.grade = dto.getGrade();
    }
    if (dto.getHeight() != null) {
      this.height = dto.getHeight();
    }
    if (dto.getHorizontalLength() != null) {
      this.horizontalLength = dto.getHorizontalLength();
    }
    if (dto.getVerticalLength() != null) {
      this.verticalLength = dto.getVerticalLength();
    }
    if (dto.getShape() != null) {
      this.shape = dto.getShape();
    }
    if (dto.getOrientation() != null) {
      this.orientation = dto.getOrientation();
    }
    if (dto.getStandardOxygen() != null) {
      this.standardOxygen = dto.getStandardOxygen();
    }
    if (dto.getRemark() != null) {
      this.remark = dto.getRemark();
    }
  }
  
}