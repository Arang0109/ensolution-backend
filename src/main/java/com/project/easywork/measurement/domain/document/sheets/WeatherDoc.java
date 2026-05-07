package com.project.easywork.measurement.domain.document.sheets;

import com.project.easywork.measurement.domain.constants.WeatherCondition;
import com.project.easywork.measurement.domain.constants.WindDirection;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Builder(toBuilder = true)
public class WeatherDoc {
  
  private WeatherPressureDoc pressure;
  
  private WeatherCondition weatherCondition;
  @Field(targetType = FieldType.DECIMAL128) private BigDecimal temperature;
  @Field(targetType = FieldType.DECIMAL128) private BigDecimal humidity;
  private WindDirection windDirection;
  @Field(targetType = FieldType.DECIMAL128) private BigDecimal windSpeed;
  
  // 계산
  private BigDecimal Pa; // 대기압 (mmHg)
  
  private static BigDecimal scale(BigDecimal value) {
    return value == null ? null : value.setScale(1, RoundingMode.HALF_UP);
  }
  
  public WeatherDoc merge(WeatherDoc doc) {
    if (doc == null) return this;
    
    return this.toBuilder()
        .pressure(
            doc.pressure != null
                ? (this.pressure == null ? doc.pressure : this.pressure.merge(doc.pressure))
                : this.pressure
        )
        .weatherCondition(doc.weatherCondition != null ? doc.weatherCondition : this.weatherCondition)
        .temperature(doc.temperature != null ? scale(doc.temperature) : this.temperature)
        .humidity(doc.humidity != null ? scale(doc.humidity) : this.humidity)
        .windDirection(doc.windDirection != null ? doc.windDirection : this.windDirection)
        .windSpeed(doc.windSpeed != null ? scale(doc.windSpeed) : this.windSpeed)
        .build();
  }
  
  @Getter
  @Builder(toBuilder = true)
  public static class WeatherPressureDoc {
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal pressure;
    private String unit;
    
    public WeatherPressureDoc merge(WeatherPressureDoc doc) {
      if (doc == null) return this;
      
      return this.toBuilder()
          .pressure(doc.pressure != null ? scale(doc.pressure) : this.pressure)
          .unit(doc.unit != null ? doc.unit : this.unit)
          .build();
    }
  }
}