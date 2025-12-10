package com.project.easywork.schedule.domain.dto;

import com.project.easywork.client.domain.dto.company.CompanyResponseDto;
import com.project.easywork.client.domain.dto.stack.StackDetailResponseDto;
import com.project.easywork.client.domain.dto.workplace.WorkplaceResponseDto;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class ScheduleDetailResponseDto {
  private ScheduleResponseDto schedule;
  private StackDetailResponseDto stack;
  private WorkplaceResponseDto workplace;
  private CompanyResponseDto company;
}