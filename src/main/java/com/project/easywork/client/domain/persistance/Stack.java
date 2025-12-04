
package com.project.easywork.client.domain.persistance;

import com.project.easywork.client.domain.dto.stack.StackUpdateRequestDto;
import com.project.easywork.common.constant.Grade;
import com.project.easywork.common.constant.Shape;
import com.project.easywork.common.constant.Orientation;
import com.project.easywork.schedule.domain.persistance.Schedule;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@ToString
@Table(name = "stack")
public class Stack {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private Long id;
  
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
  
  @CreationTimestamp
  @Column(name = "created_at", nullable = false)
  private LocalDate createdAt;
  
  @UpdateTimestamp
  @Column(name = "modified_at", nullable = false)
  private LocalDate modifiedAt;
  
  @OneToMany(mappedBy = "stack", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  private List<Prevention> preventions = new ArrayList<>();
  
  @OneToMany(mappedBy = "stack", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  private List<StackMeasurement> stackMeasurements = new ArrayList<>();
  
  @OneToMany(mappedBy = "stack")
  @ToString.Exclude
  private List<Schedule> schedules = new ArrayList<>();
  
  public void update(StackUpdateRequestDto dto) {
    Optional.ofNullable(dto.getName())
        .filter(name -> !name.isBlank())
        .ifPresent(this::setName);
    
    Optional.ofNullable(dto.getSemsNumber())
        .filter(sems -> !sems.isBlank())
        .ifPresent(this::setSemsNumber);
    
    Optional.ofNullable(dto.getGrade())
        .ifPresent(this::setGrade);
    
    Optional.ofNullable(dto.getHeight())
        .ifPresent(this::setHeight);
    
    Optional.ofNullable(dto.getHorizontalLength())
        .ifPresent(this::setHorizontalLength);
    
    Optional.ofNullable(dto.getVerticalLength())
        .ifPresent(this::setVerticalLength);
    
    Optional.ofNullable(dto.getShape())
        .ifPresent(this::setShape);
    
    Optional.ofNullable(dto.getOrientation())
        .ifPresent(this::setOrientation);
    
    Optional.ofNullable(dto.getRemark())
        .filter(remark -> !remark.isBlank())
        .ifPresent(this::setRemark);
  }
}