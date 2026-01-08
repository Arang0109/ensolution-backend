package com.project.easywork.client.domain.dto.stack;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class MeasurementHistoryD {
  private Long scheduleId;
  private LocalDate measureDate;
  private String teamName;
  private List<String> measurements;
}