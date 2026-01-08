package com.project.easywork.client.domain.dto.stack_measurement;

import com.project.easywork.client.domain.Cycle;
import com.project.easywork.pollutant.domain.dto.PollutantD;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
public class StackMeasurementResponseDto {
  @Schema(
      description = "시설 내 측정물질 ID (PK)",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;
  
  private Long stackId;
  private PollutantD pollutant;
  private Cycle cycle;
  private Double allowance;
  
  @Schema(description = "생성날짜") private LocalDate createdAt;
  @Schema(description = "수정날짜") private LocalDate modifiedAt;
}
