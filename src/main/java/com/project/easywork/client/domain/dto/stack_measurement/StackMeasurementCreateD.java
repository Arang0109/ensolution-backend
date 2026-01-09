package com.project.easywork.client.domain.dto.stack_measurement;

import com.project.easywork.client.domain.Cycle;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StackMeasurementCreateD {
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
  @Min(value = 0, message = "0 이상의 값을 입력해주세요.")
  @Max(value = 100, message = "100 이하의 값을 입력해주세요.")
  private Double allowance;
}
