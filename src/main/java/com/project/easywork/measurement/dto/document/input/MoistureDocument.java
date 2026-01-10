package com.project.easywork.measurement.dto.document.input;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Builder(toBuilder = true)
public class MoistureDocument {
  
  private WeightDocument weight;
  private GasMeterTemperatureDocument gasMeterTemperature;
  private DryGasVolumeDocument dryGasVolume;
  
  @Field(targetType = FieldType.DECIMAL128)
  private BigDecimal suctionVelocity;        // 예: 소수 3자리
  
  @Field(targetType = FieldType.DECIMAL128)
  private BigDecimal gasMeterGaugePressure;  // 예: 소수 2자리
  
  /**
   * Mongo 저장 전 자릿수 정규화
   */
  public MoistureDocument normalize() {
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
  }
  
  // ------------------------
  // 건조가스 부피
  // ------------------------
  @Getter
  @Builder(toBuilder = true)
  public static class DryGasVolumeDocument {
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal before;
    
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal after;
    
    public DryGasVolumeDocument normalize() {
      return this.toBuilder()
          .before(scale(before, 3))
          .after(scale(after, 3))
          .build();
    }
  }
}