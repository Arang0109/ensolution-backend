package com.project.easywork.measurement.dto.command;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "기상 정보 입력 DTO")
public record WeatherCommandDto(
    
    @Schema(description = "대기압 정보 (값 + 단위)")
    WeatherPressure pressure,
    
    @Schema(description = "기상", example = "맑음")
    String weatherCondition,
    
    @Schema(description = "기온(°C)", example = "24.1")
    Double temperature,
    
    @Schema(description = "습도(%)", example = "89.7")
    Double humidity,
    
    @Schema(description = "풍향", example = "서")
    String windDirection,
    
    @Schema(description = "풍속(m/s)", example = "2.3")
    Double windSpeed

) {
  
  @Schema(description = "대기압 정보 DTO")
  public record WeatherPressure(
      
      @Schema(description = "대기압 값", example = "1005")
      Double pressure,
      
      @Schema(description = "압력 단위", example = "hPa")
      String unit
  ) {}
}
