package com.project.easywork.measurement.dto.document.input;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

@Getter
@Builder(toBuilder = true)
public class MeasurementPointDocument {
  // ===== 입력 영역 =====
  private BigDecimal gasTemperature;        // 배출가스 온도
  private BigDecimal dynamicPressure;       // 동압
  private BigDecimal staticPressure;        // 정압
  
  private ParticularEquipmentTemperature equipmentTemperature;
  private ParticularEquipmentVolume equipmentVolume;
  
  private BigDecimal measureTime;           // 측정 시간
  private BigDecimal vacuumGaugePressure;   // 진공게이지 압력
  private BigDecimal finalImpingerTemperature; // 최종 임핀저 온도
  
  // ===== 계산 영역 =====
  private BigDecimal gasVelocity;           // 유속
  private BigDecimal gasDensity;            // 가스 밀도
  
  private BigDecimal collectedWaterVolume;
  
  private BigDecimal kFactor;               // K 계수
  private BigDecimal orificeDifferentialPressure;
  private BigDecimal nozzleSize;
  
  @Getter
  @Builder(toBuilder = true)
  public static class ParticularEquipmentTemperature {
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal inletTemperature;   // 기존 in
    
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal outletTemperature;  // 기존 out
    
    // 계산 영역
    private BigDecimal averageTemperature; // 기존 avgTemperature
  }
  
  @Getter
  @Builder(toBuilder = true)
  public static class ParticularEquipmentVolume {
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal beforeVolume;   // 기존 before
    
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal afterVolume;    // 기존 after
    
    // 계산 영역
    private BigDecimal requiredVolume;
    private BigDecimal expectedVolume;
    private BigDecimal actualCollectedVolume;
  }
}