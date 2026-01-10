package com.project.easywork.plan.domain.persistance;

import com.project.easywork.client.domain.persistance.StackMeasurement;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Getter
@Table(name = "plan_measurements")
public class PlanMeasurement {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private Long id;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "plan_id")
  private Plan plan;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "stack_measurement_id")
  private StackMeasurement stackMeasurement;
  
  public void attachPlan(Plan plan) {
    this.plan = plan;
  }
  
  public void attachStackMeasurement(StackMeasurement stackMeasurement) {this.stackMeasurement = stackMeasurement;}
  
  public Long getStackMeasurementId() {
    return this.stackMeasurement != null ? this.stackMeasurement.getId() : null;
  }
  
  public void update(PlanMeasurement source) {
    if (source.stackMeasurement != null) {
      this.stackMeasurement = source.stackMeasurement;
    }
  }
}
