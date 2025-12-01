package com.project.easywork.client.domain.dto.stack_measurement;

import com.project.easywork.common.constant.Cycle;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class StackMeasurementCreateRequestDto {
  @Schema(description = "측정시설 ID (FK)", example = "1")
  @NotNull(message = "필수 입력")
  private Long stackId;
  
  @Schema(description = "측정물질 ID (FK)", example = "1")
  @NotNull(message = "필수 입력")
  private Long pollutantId;
  
  @Schema(
      description = "측정주기",
      example = "ANNUAL"
  )
  private Cycle cycle;
  
  @Schema(description = "허용 기준치", example = "40")
  private Double allowance;
}
