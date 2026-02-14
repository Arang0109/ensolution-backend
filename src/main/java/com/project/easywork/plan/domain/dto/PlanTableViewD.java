package com.project.easywork.plan.domain.dto;

import com.project.easywork.plan.domain.MeasureField;
import com.project.easywork.plan.domain.PlanStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PlanTableViewD {
  private Long id;
  private MeasureField measureField;
  private LocalDate measureDate;
  private String measurementType;
  
  private String companyName;
  private String workplaceName;
  private String stackName;
  private String teamName;
  
  private List<String> measurements;
  
  private PlanStatus status;
  private LocalDate createdAt;
}
