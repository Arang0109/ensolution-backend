package com.project.easywork.client.domain.dto.target;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class TargetResponseDto {
  
  @Schema(
      description = "대상물질 ID (PK)",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;
  
  private Long preventionId;
  private String targetSubstance;
  private Double removalEfficiency;
  
  @Schema(description = "생성날짜") private LocalDate createdAt;
  @Schema(description = "수정날짜") private LocalDate modifiedAt;
}
