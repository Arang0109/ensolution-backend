package com.project.easywork.plan.domain.dto;

import com.project.easywork.measurement.dto.MeasurementStatus;
import com.project.easywork.measurement.dto.document.input.*;
import com.project.easywork.measurement.dto.document.result.MeasurementResultDocument;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PlanDetailD {
  private PlanD plan;
  private MeasurementStatus status;
  private Integer measurementPointCnt;
  
  private PreInfoDocument preInfo;
  private ClientDocument client;
  private WeatherDocument weather;
  private MoistureDocument moisture;
  private ExhaustGasDocument exhaustGas;
  
  private MeasurementResultDocument result;
}