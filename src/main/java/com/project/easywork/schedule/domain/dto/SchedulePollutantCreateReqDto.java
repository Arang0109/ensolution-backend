package com.project.easywork.schedule.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class SchedulePollutantCreateReqDto {
  private Long scheduleId;
  private Long stackMeasurementId;
}