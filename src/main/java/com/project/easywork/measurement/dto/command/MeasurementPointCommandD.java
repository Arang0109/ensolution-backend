package com.project.easywork.measurement.dto.command;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "각 측정 포인트의 측정 데이터")
public record MeasurementPointCommandD(
    BigDecimal gasTemperature,
    BigDecimal dynamicPressure,
    BigDecimal staticPressure,
    
    ParticularEquipmentTemperature equipmentTemperature,
    ParticularEquipmentVolume equipmentVolume,
    
    BigDecimal measureTime,
    BigDecimal vacuumGaugePressure,
    BigDecimal finalImpingerTemperature
) {
  public record ParticularEquipmentTemperature(
      BigDecimal inletTemperature,
      BigDecimal outletTemperature
  ) {}
  
  public record ParticularEquipmentVolume(
      BigDecimal beforeVolume,
      BigDecimal afterVolume
  ) {}
}
