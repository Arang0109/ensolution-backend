package com.project.easywork.client.domain.dto.stack;

import com.project.easywork.client.domain.Cycle;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class MeasurementListD {
  private Long id;
  private Long pollutantId;
  private String nameKr;
  private String nameEn;
  private Cycle cycle;
  private Double allowance;
  @Schema(description = "수정날짜") private LocalDate modifiedAt;
}