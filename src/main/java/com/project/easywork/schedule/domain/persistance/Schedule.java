package com.project.easywork.schedule.domain.persistance;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.schedule.domain.ScheduleStatus;
import com.project.easywork.schedule.domain.dto.ScheduleStatusUpdateReqDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "schedule")
public class Schedule {
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
  private ScheduleStatus status = ScheduleStatus.MEASURING;
  
  @CreationTimestamp
  @Column(name = "created_at", nullable = false)
  private LocalDate createdAt;
  
  @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ScheduleMeasurement> measurements = new ArrayList<>();
  
  public void attachStack(Stack stack) {
    this.stack = stack;
  }
  
  public void attachTeam(Team team) {
    this.team = team;
  }
  
  public void updateStatus(ScheduleStatusUpdateReqDto dto) {
    this.status = dto.getStatus();
  }
  
  public void addMeasurement(ScheduleMeasurement measurement) {
    
    if (this.measurements.contains(measurement)) {
      return;
    }
    
    if (measurement == null) return;
    measurement.attachSchedule(this);
    this.measurements.add(measurement);
  }
  
  public void addMeasurements(List<ScheduleMeasurement> measurements) {
    if (measurements == null) return;
    measurements.forEach(this::addMeasurement);
  }
}
