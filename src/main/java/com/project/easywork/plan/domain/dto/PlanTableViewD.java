package com.project.easywork.plan.domain.dto;

import com.project.easywork.plan.domain.MeasurementField;
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
  private PlanStatus status;
  private MeasurementField measurementField;
  private LocalDate measureDate;
  private String measurementType;
  
  private String companyName;
  private String workplaceName;
  private String stackName;
  private String teamName;
  
  private List<String> measurementItems;
  
  private LocalDate createdAt;
}
