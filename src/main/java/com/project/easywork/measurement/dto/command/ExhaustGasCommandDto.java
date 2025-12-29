package com.project.easywork.measurement.dto.command;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record ExhaustGasCommandDto(
    
    @Schema(description = "측정점별 배출가스온도, 동압, 정압 데이터")
    List<MeasurementPoint> measurementPoints,
    
    @Schema(description = "표준산소농도(%)", example = "20.9")
    Double standardOxygen,
    
    @Schema(description = "산소농도(%)", example = "[20.9, 20.8, 20.9]")
    List<Double> o2Concentration,
    
    @Schema(description = "이산화탄소농도(%)", example = "[0.1, 0.1, 0.2]")
    List<Double> co2Concentration,
    
    @Schema(description = "일산화탄소농도(ppm)", example = "[10, 12, 11]")
    List<Double> coConcentration,
    
    @Schema(description = "질소산화물(ppm)", example = "[3.2, 3.4, 3.3]")
    List<Double> noxConcentration,
    
    @Schema(description = "황산화물(ppm)", example = "[0, 0, 0]")
    List<Double> soxConcentration
    
) {
  
  public record MeasurementPoint(
      
      DynamicPressure dynamicPressure,
      StaticPressure staticPressure,
      
      @Schema(description = "배출가스 온도(℃)", example = "121.3")
      Double gasTemperature
      
  ) {
    
    public record DynamicPressure(
        
        @Schema(description = "동압", example = "21.2")
        Double pressure,
        
        @Schema(description = "압력 단위", example = "mmH2O")
        String unit
    
    ) {}
    
    public record StaticPressure(
        
        @Schema(description = "정압", example = "-12.4")
        Double pressure,
        
        @Schema(description = "압력 단위", example = "mmH2O")
        String unit
    
    ) {}
  }
  
}
