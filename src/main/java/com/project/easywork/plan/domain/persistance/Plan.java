package com.project.easywork.plan.domain.persistance;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.plan.domain.PlanStatus;
import com.project.easywork.plan.domain.dto.StatusUpdateD;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
  
  @Column(name = "measure_date")
  private LocalDate measureDate;
  
  @Column(name = "measurement_type")
  private String measurementType;
  
  @Enumerated(EnumType.STRING)
  private PlanStatus status = PlanStatus.MEASURING;
  
  @CreationTimestamp
  @Column(name = "created_at", nullable = false)
  private LocalDate createdAt;
  
  @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PlanMeasurement> measurements = new ArrayList<>();
  
  public void attachStack(Stack stack) {
    this.stack = stack;
  }
  
  public void attachTeam(Team team) {
    this.team = team;
  }
  
  public void updateStatus(StatusUpdateD dto) {
    this.status = dto.getStatus();
  }
  
  public void updateMeasurement(PlanMeasurement measurement) {
    
    if (this.measurements.contains(measurement)) {
      return;
    }
    
    if (measurement == null) return;
    measurement.attachPlan(this);
    this.measurements.add(measurement);
  }
  
  public void updateMeasurements(List<PlanMeasurement> measurements) {
    if (measurements == null) return;
    measurements.forEach(this::updateMeasurement);
  }
}
