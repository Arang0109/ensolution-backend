package com.project.easywork.measurement.domain.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WeatherCondition {
  
  CLEAR("맑음"),
  CLOUDY("흐림"),
  RAIN("비"),
  SNOW("눈");
  
  private final String description;
}