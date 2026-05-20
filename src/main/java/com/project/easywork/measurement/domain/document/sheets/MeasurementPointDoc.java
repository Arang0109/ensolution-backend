package com.project.easywork.measurement.domain.document.sheets;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

@Getter
@Builder(toBuilder = true)
public class MeasurementPointDoc {
  // ===== 입력 영역 =====
  @JsonProperty("Ts")
  private BigDecimal Ts;        // 배출가스 온도
  @JsonProperty("Pv")
  private BigDecimal Pv;       // 동압
  @JsonProperty("Ps")
  private BigDecimal Ps;        // 정압
  
  private ParticularEquipmentTemperature equipmentTemperature;
  private ParticularEquipmentVolume equipmentVolume;
  
  private BigDecimal samplingTime;           // 채취 시간
  private BigDecimal vacuumGaugePressure;   // 진공게이지 압력
  private BigDecimal finalImpingerTemperature; // 최종 임핀저 온도
  
  // ===== 계산 영역 =====
  @JsonProperty("Vs")
  private BigDecimal Vs;           // 유속
  private BigDecimal gasDensity;            // 가스 밀도
  
  @JsonProperty("Vm")
  private BigDecimal Vm;  // 건식가스미터 채취량 (m³)
  @JsonProperty("Vlc")
  private BigDecimal Vlc;  // 채취된 물의 총량 (ml)
  
  @JsonProperty("kFactor")
  private BigDecimal kFactor;               // K 계수
  private BigDecimal orificeDp;
  private BigDecimal isokineticRatio; // 등속흡입계수
  
  public MeasurementPointDoc merge(MeasurementPointDoc doc) {
    if (doc == null) return this;
    
    return this.toBuilder()
        .Ts(doc.Ts != null ? doc.Ts : this.Ts)
        .Pv(doc.Pv != null ? doc.Pv : this.Pv)
        .Ps(doc.Ps != null ? doc.Ps : this.Ps)
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
        .samplingTime(doc.samplingTime != null ? doc.samplingTime : this.samplingTime)
        .vacuumGaugePressure(doc.vacuumGaugePressure != null ? doc.vacuumGaugePressure : this.vacuumGaugePressure)
        .finalImpingerTemperature(doc.finalImpingerTemperature != null ? doc.finalImpingerTemperature : this.finalImpingerTemperature)
        .build();
  }
  
  @Getter
  @Builder(toBuilder = true)
  public static class ParticularEquipmentTemperature {
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal inTm;   // 기존 in
    
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal outTm;  // 기존 out
    
    // 계산 영역
    private BigDecimal avgTm; // 기존 avgTemperature
    
    public ParticularEquipmentTemperature merge(ParticularEquipmentTemperature doc) {
      if (doc == null) return this;
      
      return this.toBuilder()
          .inTm(doc.inTm != null ? doc.inTm : this.inTm)
          .outTm(doc.outTm != null ? doc.outTm : this.outTm)
          .build();
    }
  }
  
  @Getter
  @Builder(toBuilder = true)
  public static class ParticularEquipmentVolume {
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal beforeVm;   // 기존 before
    
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal afterVm;    // 기존 after
    
    public ParticularEquipmentVolume merge(ParticularEquipmentVolume doc) {
      if (doc == null) return this;
      
      return this.toBuilder()
          .beforeVm(doc.beforeVm != null ? doc.beforeVm : this.beforeVm)
          .afterVm(doc.afterVm != null ? doc.afterVm : this.afterVm)
          .build();
    }
  }
}