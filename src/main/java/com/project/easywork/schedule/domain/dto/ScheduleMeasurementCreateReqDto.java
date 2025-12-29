package com.project.easywork.schedule.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ScheduleMeasurementCreateReqDto {
  private Long scheduleId;
  private Long stackMeasurementId;
}