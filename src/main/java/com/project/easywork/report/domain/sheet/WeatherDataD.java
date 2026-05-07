package com.project.easywork.report.domain.sheet;

import com.project.easywork.measurement.domain.constants.WeatherCondition;
import com.project.easywork.measurement.domain.constants.WindDirection;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WeatherDataD {
  private BigDecimal atmPressure;
  private WeatherCondition weatherCondition;
  private BigDecimal temperature;
  private BigDecimal humidity;
  private WindDirection windDirection;
  private BigDecimal windSpeed;
}
