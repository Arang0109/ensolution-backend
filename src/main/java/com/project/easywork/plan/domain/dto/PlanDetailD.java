package com.project.easywork.plan.domain.dto;

import com.project.easywork.measurement.dto.MeasurementStatus;
import com.project.easywork.measurement.dto.document.input.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PlanDetailD {
  private PlanD plan;
  private MeasurementStatus status;
  private Integer measurementPointCnt;
  
  private PreInfoDoc preInfo;
  private MeasurementEquipmentDoc equipment;
  private ClientDoc client;
  private WeatherDoc weather;
  private MoistureDoc moisture;
  private ExhaustGasDoc exhaustGas;
}