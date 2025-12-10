package com.project.easywork.schedule.domain.dto;

import com.project.easywork.schedule.domain.ScheduleStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ScheduleStatusUpdateRequestDto {
  private ScheduleStatus status;
}
