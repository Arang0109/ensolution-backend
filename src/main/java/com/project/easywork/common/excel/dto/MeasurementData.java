package com.project.easywork.common.excel.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MeasurementData {
  private WeatherData weatherData;
  private GasData gasData;
  private double[] distances;
  
  
}
