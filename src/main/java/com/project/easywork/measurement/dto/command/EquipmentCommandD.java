package com.project.easywork.measurement.dto.command;

import java.math.BigDecimal;

public record EquipmentCommandD(
    ParticularEquipment particularEquipment,
    PitotTube pitotTube
) {
  public record ParticularEquipment(
      Long equipmentId,
      String alias,
      
      BigDecimal deltaH, // 오리피스 보정 계수
      BigDecimal Yd // 가스미터 보정 계수
  ) {}
  
  public record PitotTube(
      Long equipmentId,
      String alias,
      String type
  ) {
    public record Coefficient(
        Long coefficientId,
        BigDecimal velocity,
        BigDecimal coefficient
    ) {}
  }
}
