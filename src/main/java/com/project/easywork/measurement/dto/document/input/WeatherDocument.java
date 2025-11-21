package com.project.easywork.measurement.dto.document.input;

import lombok.*;

@Getter
@Builder
public class WeatherDocument {
  
  private WeatherPressureDocument pressure;
  
  private String weatherCondition;
  private Double temperature;
  private Double humidity;
  private String windDirection;
  private Double windSpeed;
  
  @Getter
  @Builder
  public static class WeatherPressureDocument {
    private Double pressure;
    private String unit;
  }
}