package com.project.easywork.common.excel.dto;

import com.project.easywork.client.dto.list.WorkplaceProfileDto;
import com.project.easywork.client.dto.view.StackDetailDto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MeasurementReportExportDto {
  private WorkplaceProfileDto workplaceProfile;
  private StackDetailDto stackDetail;
  
  private String managerName;
}
