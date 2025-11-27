package com.project.easywork.client.domain.dto.prevention;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PreventionRegisterDto {
  @Schema(description = "방지시설", example = "세정집진시설")
  @NotBlank(message = "필수 입력")
  private String name;
  
  @Schema(description = "대상 물질", example = "질소산화물")
  private String targetSubstance;
  
  @Schema(description = "제거 효율", example = "40")
  private Integer removalEfficiency;
  
  @Schema(description = "배출구 ID (FK)", example = "1")
  @NotNull(message = "필수 입력")
  private Long stackId;
}
