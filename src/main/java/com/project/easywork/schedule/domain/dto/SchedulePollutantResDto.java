package com.project.easywork.schedule.domain.dto;

import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class SchedulePollutantResDto {
  @Schema(
      description = "일정 ID (PK)",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;
  
  private Long scheduleId;
  private StackMeasurementResponseDto stackMeasurement;
}