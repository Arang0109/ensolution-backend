package com.project.easywork.plan.domain.dto;

import com.project.easywork.measurement.dto.MeasurementStatus;
import com.project.easywork.measurement.dto.document.input.ExhaustGasDocument;
import com.project.easywork.measurement.dto.document.input.MoistureDocument;
import com.project.easywork.measurement.dto.document.input.PreInfoDocument;
import com.project.easywork.measurement.dto.document.input.WeatherDocument;
import com.project.easywork.measurement.dto.document.result.MeasurementResultDocument;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PlanDetailD {
  private PlanD plan;
  private MeasurementStatus status;
  private String vehicleNumber;// Mongo 기준 상태
  
  private PreInfoDocument preInfo;
  private WeatherDocument weather;
  private MoistureDocument moisture;
  private ExhaustGasDocument exhaustGas;
  
  private MeasurementResultDocument result;
}