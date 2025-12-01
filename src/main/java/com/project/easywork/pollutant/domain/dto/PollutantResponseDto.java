package com.project.easywork.pollutant.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class PollutantResponseDto {
  @Schema(
      description = "측정물질 ID (PK)",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;
  
  private String nameKr;
  private String nameEn;
  private String method;
  private Double samplingTime;
  private String samplingVolume;
}
