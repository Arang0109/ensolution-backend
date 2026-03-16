package com.project.easywork.client.domain.dto.target;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TargetCreateD {
  
  @Schema(description = "대상 물질", example = "질소산화물")
  private String targetSubstance;
  
  @Schema(description = "제거 효율", example = "40")
  @Size(min = 0, max = 100, message = "0~100 사이의 값을 입력해주세요.")
  private Double removalEfficiency;
}
