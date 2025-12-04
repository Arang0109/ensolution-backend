package com.project.easywork.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ScheduleUpdateRequestDto {
  private Long stackId;
  private Long teamId;
  private LocalDate measureDate;
  private String measurementType;
}
