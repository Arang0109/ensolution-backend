package com.project.easywork.measurement.dto.command;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "기상 정보 입력 DTO")
public record WeatherCommandD(
    
    @Schema(description = "대기압 정보 (값 + 단위)")
    WeatherPressure pressure,
    
    @Schema(description = "기상", example = "맑음")
    String weatherCondition,
    
    @Schema(description = "기온(°C)", example = "24.1")
    BigDecimal temperature,
    
    @Schema(description = "습도(%)", example = "89.7")
    BigDecimal humidity,
    
    @Schema(description = "풍향", example = "서")
    String windDirection,
    
    @Schema(description = "풍속(m/s)", example = "2.3")
    BigDecimal windSpeed

) {
  
  @Schema(description = "대기압 정보 DTO")
  public record WeatherPressure(
      
      @Schema(description = "대기압 값", example = "1005.0")
      BigDecimal pressure,
      
      @Schema(description = "압력 단위", example = "hPa")
      String unit
  ) {}
}
