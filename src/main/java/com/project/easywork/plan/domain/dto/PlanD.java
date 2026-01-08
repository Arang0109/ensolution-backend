package com.project.easywork.plan.domain.dto;

import com.project.easywork.plan.domain.PlanStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class PlanD {
  @Schema(
      description = "측정계획 ID (PK)",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;
  
  private Long stackId;
  private Long teamId;
  private LocalDate measureDate;
  private String measurementType;
  private PlanStatus status;
  
  @Schema(description = "생성날짜") private LocalDate createdAt;
}