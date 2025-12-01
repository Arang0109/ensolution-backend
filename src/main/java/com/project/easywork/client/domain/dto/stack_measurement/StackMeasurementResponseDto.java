package com.project.easywork.client.domain.dto.stack_measurement;

import com.project.easywork.common.constant.Cycle;
import com.project.easywork.pollutant.domain.dto.PollutantResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class StackMeasurementResponseDto {
  @Schema(
      description = "시설 내 측정물질 ID (PK)",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;
  
  private Long stackId;
  private PollutantResponseDto pollutant;
  private Cycle cycle;
  private Double allowance;
  
  @Schema(description = "생성날짜") private LocalDate createdAt;
  @Schema(description = "수정날짜") private LocalDate modifiedAt;
}
