package com.project.easywork.schedule.domain.persistance;

import com.project.easywork.client.domain.persistance.StackMeasurement;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "schedule_pollutant")
public class SchedulePollutant {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private Long id;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "schedule_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  @ToString.Exclude
  private Schedule schedule;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "stack_measurement_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  @ToString.Exclude
  private StackMeasurement stackMeasurement;
  
  public void attachSchedule(Schedule schedule) {
    this.schedule = schedule;
  }
  
  public void attachStackMeasurement(StackMeasurement stackMeasurement) {
    this.stackMeasurement = stackMeasurement;
  }
}
