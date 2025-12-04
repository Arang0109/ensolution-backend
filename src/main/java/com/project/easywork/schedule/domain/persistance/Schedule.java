package com.project.easywork.schedule.domain.persistance;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.common.constant.ScheduleStatus;
import com.project.easywork.schedule.domain.dto.ScheduleStatusUpdateRequestDto;
import com.project.easywork.schedule.domain.dto.ScheduleUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
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
  
  private ScheduleStatus status;
  
  @CreationTimestamp
  @Column(name = "created_at", nullable = false)
  private LocalDate createdAt;
  
  private void update(Stack stack, Team team, ScheduleUpdateRequestDto dto) {
    Optional.ofNullable(stack)
        .ifPresent(this::setStack);
    
    Optional.ofNullable(team)
        .ifPresent(this::setTeam);
    
    Optional.ofNullable(dto.getMeasureDate())
        .ifPresent(this::setMeasureDate);
    
    Optional.ofNullable(dto.getMeasurementType())
        .ifPresent(this::setMeasurementType);
  }
  
  private void updateStatus(ScheduleStatusUpdateRequestDto dto) {
    this.status = dto.getStatus();
  }
}
