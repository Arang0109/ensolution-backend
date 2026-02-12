package com.project.easywork.plan.domain.dto;

import com.project.easywork.plan.domain.MeasureField;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PlanCreateD {
  @NotNull(message = "측정시설 ID는 필수 값입니다.")
  private Long stackId;
  
  @NotNull(message = "측정팀 ID는 필수 값입니다.")
  private Long teamId;
  
  @NotBlank(message = "측정분야 선택하세요.")
  private MeasureField measureField;
  
  @NotNull(message = "측정일자는 필수 값입니다.")
  private LocalDate measureDate;
  
  @NotBlank(message = "측정용도를 선택하세요.")
  private String measurementType;
  
  private List<Long> measurementIds = new ArrayList<>();
}
