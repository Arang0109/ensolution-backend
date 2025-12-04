package com.project.easywork.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ScheduleCreateRequestDto {
  @NotNull(message = "측정시설 ID는 필수 값입니다.")
  private Long stackId;
  
  @NotNull(message = "측정팀 ID는 필수 값입니다.")
  private Long teamId;
  
  @NotNull(message = "측정일자는 필수 값입니다.")
  private LocalDate measureDate;
  
  @NotBlank(message = "측정 목적을 선택하세요.")
  private String measurementType;
}
