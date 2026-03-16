package com.project.easywork.measurement.dto.document.input;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Builder(toBuilder = true)
public class MoistureDoc {
  
  private WeightDocument weight;
  private GasMeterTemperatureDocument gasMeterTemperature;
  private DryGasVolumeDocument dryGasVolume;
  
  @Field(targetType = FieldType.DECIMAL128) private BigDecimal suctionVelocity;
  @Field(targetType = FieldType.DECIMAL128) private BigDecimal gasMeterGaugePressure;
  
  /**
   * 계산 영역
   * 수분량 (%)
   */
  private BigDecimal moistureRatio;
  
  public MoistureDoc normalize() {
    return this.toBuilder()
        .suctionVelocity(scale(suctionVelocity, 1))
        .gasMeterGaugePressure(scale(gasMeterGaugePressure, 1))
        .weight(weight != null ? weight.normalize() : null)
        .gasMeterTemperature(gasMeterTemperature != null ? gasMeterTemperature.normalize() : null)
        .dryGasVolume(dryGasVolume != null ? dryGasVolume.normalize() : null)
        .build();
  }
  
  private static BigDecimal scale(BigDecimal value, int scale) {
    return value == null ? null : value.setScale(scale, RoundingMode.HALF_UP);
  }
  
  public MoistureDoc merge(MoistureDoc doc) {
    if (doc == null) return this;
    
    return this.toBuilder()
        .weight(
            doc.weight != null
                ? (this.weight == null ? doc.weight : this.weight.merge(doc.weight))
                : this.weight
        )
        .gasMeterTemperature(
            doc.gasMeterTemperature != null
                ? (this.gasMeterTemperature == null
                ? doc.gasMeterTemperature
                : this.gasMeterTemperature.merge(doc.gasMeterTemperature))
                : this.gasMeterTemperature
        )
        .dryGasVolume(
            doc.dryGasVolume != null
                ? (this.dryGasVolume == null
                ? doc.dryGasVolume
                : this.dryGasVolume.merge(doc.dryGasVolume))
                : this.dryGasVolume
        )
        .suctionVelocity(
            doc.suctionVelocity != null
                ? doc.suctionVelocity
                : this.suctionVelocity
        )
        .gasMeterGaugePressure(
            doc.gasMeterGaugePressure != null
                ? doc.gasMeterGaugePressure
                : this.gasMeterGaugePressure
        )
        .build();
  }
  
  // ------------------------
  // 흡습병 무게
  // ------------------------
  @Getter
  @Builder(toBuilder = true)
  public static class WeightDocument {
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal before;
    
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal after;
    
    public WeightDocument normalize() {
      return this.toBuilder()
          .before(scale(before, 2))
          .after(scale(after, 2))
          .build();
    }
    
    public WeightDocument merge(WeightDocument doc) {
      if (doc == null) return this;
      
      return this.toBuilder()
          .before(doc.before != null ? doc.before : this.before)
          .after(doc.after != null ? doc.after : this.after)
          .build();
    }
  }
  
  // ------------------------
  // 가스미터 온도
  // ------------------------
  @Getter
  @Builder(toBuilder = true)
  public static class GasMeterTemperatureDocument {
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal in;
    
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal out;
    
    public GasMeterTemperatureDocument normalize() {
      return this.toBuilder()
          .in(scale(in, 1))
          .out(scale(out, 1))
          .build();
    }
    
    public GasMeterTemperatureDocument merge(GasMeterTemperatureDocument doc) {
      if (doc == null) return this;
      
      return this.toBuilder()
          .in(doc.in != null ? doc.in : this.in)
          .out(doc.out != null ? doc.out : this.out)
          .build();
    }
  }
  
  // ------------------------
  // 건조가스 부피
  // ------------------------
  @Getter
  @Builder(toBuilder = true)
  public static class DryGasVolumeDocument {
    @Field(targetType = FieldType.DECIMAL128) private BigDecimal before;
    @Field(targetType = FieldType.DECIMAL128) private BigDecimal after;
    
    public DryGasVolumeDocument normalize() {
      return this.toBuilder()
          .before(scale(before, 3))
          .after(scale(after, 3))
          .build();
    }
    
    public DryGasVolumeDocument merge(DryGasVolumeDocument doc) {
      if (doc == null) return this;
      
      return this.toBuilder()
          .before(doc.before != null ? doc.before : this.before)
          .after(doc.after != null ? doc.after : this.after)
          .build();
    }
  }
}