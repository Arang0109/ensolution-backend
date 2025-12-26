package com.project.easywork.schedule.domain.dto;

import com.project.easywork.schedule.domain.ScheduleStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ScheduleTableViewDto {
  private Long id;
  private LocalDate measureDate;
  private String measurementType;
  
  private String companyName;
  private String workplaceName;
  private String stackName;
  private String teamName;
  
  private List<String> measurements;
  
  private ScheduleStatus status;
  private LocalDate createdAt;
}
