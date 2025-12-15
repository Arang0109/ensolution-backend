package com.project.easywork.schedule.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class SchedulePollutantCreateReqDto {
  @NotNull(message = "시설 측정항목 ID는 필수 값입니다.")
  private Long stackMeasurementId;
}