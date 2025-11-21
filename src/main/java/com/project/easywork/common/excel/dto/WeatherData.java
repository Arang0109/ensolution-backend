package com.project.easywork.common.excel.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WeatherData {
  // 대기압
  private double atmosphericPressure;
  
  // 기상
  private String weatherCondition;
  
  // 대기온도
  private double temperature;
  
  // 습도
  private double humidity;
  
  // 풍향
  private String windDirection;
  
  // 풍속
  private double windSpeed;
}
