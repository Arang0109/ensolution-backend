package com.project.easywork.client.domain.dto.target;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TargetCreateRequestDto {
  
  @Schema(description = "방지시설 ID (FK)", example = "1")
  @NotNull(message = "필수 입력")
  private Long preventionId;
  
  @Schema(description = "대상 물질", example = "질소산화물")
  private String targetSubstance;
  
  @Schema(description = "제거 효율", example = "40")
  private Double removalEfficiency;
}
