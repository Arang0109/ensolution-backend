package com.project.easywork.plan.domain.dto;

import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementD;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PlanMeasurementsD {
  @Schema(
      description = "측정항목 ID (PK)",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;
  
  private Long planId;
  private StackMeasurementD stackMeasurement;
}