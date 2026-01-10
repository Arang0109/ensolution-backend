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
  
  public void merge(WeatherDocument doc) {
    if (doc == null) return;
    
    if (doc.pressure != null) {
      if (this.pressure == null) {
        this.pressure = doc.pressure;
      } else {
        this.pressure = this.pressure.merge(doc.pressure);
      }
    }
    
    if (doc.weatherCondition != null)
      this.weatherCondition = doc.weatherCondition;
    
    if (doc.temperature != null)
      this.temperature = doc.temperature;
    
    if (doc.humidity != null)
      this.humidity = doc.humidity;
    
    if (doc.windDirection != null)
      this.windDirection = doc.windDirection;
    
    if (doc.windSpeed != null)
      this.windSpeed = doc.windSpeed;
  }
  
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
    
    public WeatherPressureDocument merge(WeatherPressureDocument doc) {
      if (doc == null) return this;
      
      return this.toBuilder()
          .pressure(doc.pressure != null ? doc.pressure : this.pressure)
          .unit(doc.unit != null ? doc.unit : this.unit)
          .build();
    }
    
    public WeatherPressureDocument normalize() {
      return this.toBuilder()
          .pressure(scale(pressure))
          .unit(unit)
          .build();
    }
  }
}