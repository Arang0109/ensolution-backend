package com.project.easywork.schedule.domain.dto;

import com.project.easywork.client.domain.dto.company.CompanyD;
import com.project.easywork.client.domain.dto.stack.StackDetailD;
import com.project.easywork.client.domain.dto.workplace.WorkplaceD;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ScheduleDetailResDto {
  private ScheduleResDto schedule;
  private List<ScheduleMeasurementResDto> measurements;
  private StackDetailD stack;
  private WorkplaceD workplace;
  private CompanyD company;
  
  private Integer measurementPoint;
}