package com.project.easywork.measurement.dto.document.input;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Builder(toBuilder = true)
public class WeatherDocument {
  
  private WeatherPressureDocument pressure;
  
  private String weatherCondition;
  @Field(targetType = FieldType.DECIMAL128)
  private BigDecimal temperature;
  @Field(targetType = FieldType.DECIMAL128)
  private BigDecimal humidity;
  private String windDirection;
  @Field(targetType = FieldType.DECIMAL128)
  private BigDecimal windSpeed;
  
  public WeatherDocument normalize() {
    return this.toBuilder()
        .pressure(pressure != null ? pressure.normalize() : null)
        .temperature(scale(temperature))
        .humidity(scale(humidity))
        .windDirection(windDirection)
        .windSpeed(scale(windSpeed))
        .build();
  }
  
  private static BigDecimal scale(BigDecimal value) {
    return value == null ? null : value.setScale(1, RoundingMode.HALF_UP);
  }
  
  @Getter
  @Builder(toBuilder = true)
  public static class WeatherPressureDocument {
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal pressure;
    private String unit;
    
    public WeatherPressureDocument normalize() {
      return this.toBuilder()
          .pressure(scale(pressure))
          .unit(unit)
          .build();
    }
  }
}