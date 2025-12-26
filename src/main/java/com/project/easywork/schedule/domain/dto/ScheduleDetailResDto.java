package com.project.easywork.schedule.domain.dto;

import com.project.easywork.client.domain.dto.company.CompanyResponseDto;
import com.project.easywork.client.domain.dto.stack.StackDetailResponseDto;
import com.project.easywork.client.domain.dto.workplace.WorkplaceResponseDto;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class ScheduleDetailResDto {
  private ScheduleResDto schedule;
  private List<ScheduleMeasurementResDto> measurements;
  private StackDetailResponseDto stack;
  private WorkplaceResponseDto workplace;
  private CompanyResponseDto company;
  
  private Integer measurementPoint;
}