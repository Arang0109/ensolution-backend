package com.project.easywork.measurement.dto.document.input;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

@Getter
@Builder(toBuilder = true)
public class MeasurementPointDoc {
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
  
  public MeasurementPointDoc merge(MeasurementPointDoc doc) {
    if (doc == null) return this;
    
    return this.toBuilder()
        .gasTemperature(
            doc.gasTemperature != null ? doc.gasTemperature : this.gasTemperature
        )
        .dynamicPressure(
            doc.dynamicPressure != null ? doc.dynamicPressure : this.dynamicPressure
        )
        .staticPressure(
            doc.staticPressure != null ? doc.staticPressure : this.staticPressure
        )
        .equipmentTemperature(
            doc.equipmentTemperature != null
                ? (this.equipmentTemperature == null
                ? doc.equipmentTemperature
                : this.equipmentTemperature.merge(doc.equipmentTemperature))
                : this.equipmentTemperature
        )
        .equipmentVolume(
            doc.equipmentVolume != null
                ? (this.equipmentVolume == null
                ? doc.equipmentVolume
                : this.equipmentVolume.merge(doc.equipmentVolume))
                : this.equipmentVolume
        )
        .measureTime(
            doc.measureTime != null ? doc.measureTime : this.measureTime
        )
        .vacuumGaugePressure(
            doc.vacuumGaugePressure != null ? doc.vacuumGaugePressure : this.vacuumGaugePressure
        )
        .finalImpingerTemperature(
            doc.finalImpingerTemperature != null
                ? doc.finalImpingerTemperature
                : this.finalImpingerTemperature
        )
        .build();
  }
  
  @Getter
  @Builder(toBuilder = true)
  public static class ParticularEquipmentTemperature {
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal inletTemperature;   // 기존 in
    
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal outletTemperature;  // 기존 out
    
    // 계산 영역
    private BigDecimal averageTemperature; // 기존 avgTemperature
    
    public ParticularEquipmentTemperature merge(ParticularEquipmentTemperature doc) {
      if (doc == null) return this;
      
      return this.toBuilder()
          .inletTemperature(doc.inletTemperature != null ? doc.inletTemperature : this.inletTemperature)
          .outletTemperature(doc.outletTemperature != null ? doc.outletTemperature : this.outletTemperature)
          .build();
    }
  }
  
  @Getter
  @Builder(toBuilder = true)
  public static class ParticularEquipmentVolume {
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal beforeVolume;   // 기존 before
    
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal afterVolume;    // 기존 after
    
    public ParticularEquipmentVolume merge(ParticularEquipmentVolume doc) {
      if (doc == null) return this;
      
      return this.toBuilder()
          .beforeVolume(doc.beforeVolume != null ? doc.beforeVolume : this.beforeVolume)
          .afterVolume(doc.afterVolume != null ? doc.afterVolume : this.afterVolume)
          .build();
    }
    
    // 계산 영역
    private BigDecimal requiredVolume;
    private BigDecimal expectedVolume;
    private BigDecimal actualCollectedVolume;
  }
}