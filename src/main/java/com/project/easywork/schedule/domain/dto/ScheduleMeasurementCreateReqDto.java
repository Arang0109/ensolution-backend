package com.project.easywork.schedule.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class ScheduleMeasurementCreateReqDto {
  private Long scheduleId;
  private Long stackMeasurementId;
}