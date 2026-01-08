package com.project.easywork.plan.domain.dto;

import com.project.easywork.client.domain.dto.company.CompanyD;
import com.project.easywork.client.domain.dto.stack.StackDetailD;
import com.project.easywork.client.domain.dto.workplace.WorkplaceD;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PlanDetailD {
  private PlanD plan;
  private List<PlanMeasurementsD> measurements;
  private StackDetailD stack;
  private WorkplaceD workplace;
  private CompanyD company;
  
  private Integer measurementPoint;
}