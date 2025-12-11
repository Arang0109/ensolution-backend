package com.project.easywork.schedule.domain.dto;

import com.project.easywork.schedule.domain.ScheduleStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class ScheduleResDto {
  @Schema(
      description = "일정 ID (PK)",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;
  
  private Long stackId;
  private Long teamId;
  private LocalDate measureDate;
  private String measurementType;
  private ScheduleStatus status;
  
  @Schema(description = "생성날짜") private LocalDate createdAt;
}