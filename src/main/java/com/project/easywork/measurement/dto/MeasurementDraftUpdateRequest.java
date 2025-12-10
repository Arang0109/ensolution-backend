package com.project.easywork.measurement.dto;

import com.project.easywork.measurement.dto.document.input.*;
import com.project.easywork.measurement.dto.document.result.MeasurementResultDocument;
import lombok.Getter;

@Getter
public class MeasurementDraftUpdateRequest {
  private PreInfoDocument preInfo;
  private WeatherDocument weather;
  private MoistureDocument moisture;
  private ExhaustGasDocument exhaustGas;
  private MeasurementResultDocument result;
}