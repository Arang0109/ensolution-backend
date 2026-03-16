package com.project.easywork.plan.domain.persistance;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.measurement.dto.SaveDraftCommandD;
import com.project.easywork.plan.domain.MeasurementField;
import com.project.easywork.plan.domain.PlanStatus;
import com.project.easywork.measurement.dto.StatusUpdateCommandD;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Getter
@Table(name = "plan")
public class Plan {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private Long id;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "stack_id", nullable = false)
  @ToString.Exclude
  private Stack stack;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "team_id", nullable = false)
  @ToString.Exclude
  private Team team;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "measurement_field")
  private MeasurementField measurementField;
  
  @Column(name = "measure_date")
  private LocalDate measureDate;
  
  @Column(name = "measurement_type")
  private String measurementType;
  
  @Enumerated(EnumType.STRING)
  private PlanStatus status = PlanStatus.MEASURING;
  
  @CreationTimestamp
  @Column(name = "created_at", nullable = false)
  private LocalDate createdAt;
  
  public void attachStack(Stack stack) {
    this.stack = stack;
  }
  
  public void attachTeam(Team team) {
    this.team = team;
  }
  
  public void createPlan() {
    this.status = PlanStatus.MEASURING;
  }
  
  public void updateStatus(StatusUpdateCommandD dto) {
    this.status = dto.status();
  }
  
  public void updatePlan(SaveDraftCommandD request) {
    this.measureDate = request.measureDate() == null ? this.measureDate : request.measureDate();
    this.measurementType = request.measurementType() == null ? this.measurementType : request.measurementType();
    this.measurementField = request.measurementField() == null ? this.measurementField : request.measurementField();
  }
}
