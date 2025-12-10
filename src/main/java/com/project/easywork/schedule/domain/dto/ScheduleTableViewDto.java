package com.project.easywork.schedule.domain.dto;

import com.project.easywork.schedule.domain.ScheduleStatus;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class ScheduleTableViewDto {
  private Long id;
  private LocalDate measureDate;
  private String measurementType;
  private ScheduleStatus status;
  
  private Long workplaceId;
  private String workplaceName;
  
  private Long stackId;
  private String stackName;
  
  private Long teamId;
  private String teamName;
  
  private LocalDate createdAt;
}
