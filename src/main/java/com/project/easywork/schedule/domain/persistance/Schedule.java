package com.project.easywork.schedule.domain.persistance;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.schedule.domain.ScheduleStatus;
import com.project.easywork.schedule.domain.dto.ScheduleStatusUpdateReqDto;
import com.project.easywork.schedule.domain.dto.ScheduleUpdateReqDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
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
  private List<SchedulePollutant> pollutants = new ArrayList<>();
  
  public void update(Stack stack, Team team, ScheduleUpdateReqDto dto) {
    Optional.ofNullable(stack)
        .ifPresent(this::setStack);
    
    Optional.ofNullable(team)
        .ifPresent(this::setTeam);
    
    Optional.ofNullable(dto.getMeasureDate())
        .ifPresent(this::setMeasureDate);
    
    Optional.ofNullable(dto.getMeasurementType())
        .ifPresent(this::setMeasurementType);
  }
  
  public void updateStatus(ScheduleStatusUpdateReqDto dto) {
    this.status = dto.getStatus();
  }
  
  public void addPollutant(SchedulePollutant pollutant) {
    if (pollutant == null) return;
    
    pollutants.add(pollutant);
    pollutant.setSchedule(this);
  }
}
