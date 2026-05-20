package com.project.easywork.measurement.domain.document.sheets;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MeasureDataDoc {
  
  // 측정점 수
  private Integer measurementPointCnt;
  
  // 평균 배출가스 온도
  @Field(targetType = FieldType.DECIMAL128) private BigDecimal gasTemperatureAverage;
  // 평균 배출가스 동압
  @Field(targetType = FieldType.DECIMAL128) private BigDecimal dynamicPressureAverage;
  // 평균 배출가스 정압
  @Field(targetType = FieldType.DECIMAL128) private BigDecimal staticPressureAverage;
  // 평균 배출가스 유속
  @Field(targetType = FieldType.DECIMAL128) private BigDecimal gasVelocityAverage;
  
  // 피토우관 계수
  @Field(targetType = FieldType.DECIMAL128) private BigDecimal pitotTubeCoefficient;
  // 표준상태의 배출가스 체적
  @Field(targetType = FieldType.DECIMAL128) private BigDecimal standardDesiredGasVolume;
  // 총 측정 시간
  @Field(targetType = FieldType.DECIMAL128) private BigDecimal measuringTime;
  // 노즐 사이즈
  @Field(targetType = FieldType.DECIMAL128) private BigDecimal nozzleSize;
  
  // 등속흡인계수 - %
  @Field(targetType = FieldType.DECIMAL128) private BigDecimal isokineticRatioAverage;
  // 배출구 유량 - Sm3/hr
  @Field(targetType = FieldType.DECIMAL128) private BigDecimal standardQuantity;
  
  public MeasureDataDoc merge(MeasureDataDoc doc) {
    if (doc == null) return this;
    
    return this.toBuilder()
      .measurementPointCnt(doc.measurementPointCnt != null ? doc.measurementPointCnt : this.measurementPointCnt)
      
      .gasTemperatureAverage(doc.gasTemperatureAverage != null ? doc.gasTemperatureAverage : this.gasTemperatureAverage)
      .dynamicPressureAverage(doc.dynamicPressureAverage != null ? doc.dynamicPressureAverage : this.dynamicPressureAverage)
      .staticPressureAverage(doc.staticPressureAverage != null ? doc.staticPressureAverage : this.staticPressureAverage)
      .gasVelocityAverage(doc.gasVelocityAverage != null ? doc.gasVelocityAverage : this.gasVelocityAverage)
      
      .pitotTubeCoefficient(doc.pitotTubeCoefficient != null ? doc.pitotTubeCoefficient : this.pitotTubeCoefficient)
      .standardDesiredGasVolume(doc.standardDesiredGasVolume != null ? doc.standardDesiredGasVolume : this.standardDesiredGasVolume)
      .measuringTime(doc.measuringTime != null ? doc.measuringTime : this.measuringTime)
      .nozzleSize(doc.nozzleSize != null ? doc.nozzleSize : this.nozzleSize)
      
      .isokineticRatioAverage(doc.isokineticRatioAverage != null ? doc.isokineticRatioAverage : this.isokineticRatioAverage)
      .standardQuantity(doc.standardQuantity != null ? doc.standardQuantity : this.standardQuantity)
      
      .build();
  }
}
