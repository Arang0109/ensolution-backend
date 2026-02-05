package com.project.easywork.measurement.dto.document.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementEquipmentDoc {
  
  private ParticleSamplerSnapshot particleSampler;
  private GasSamplerSnapshot gasSampler;
  private PitotTubeSnapshot pitotTube;
  private NozzleSnapshot nozzle;
  
  @Getter
  @Builder(toBuilder = true)
  public static class ParticleSamplerSnapshot {
    private String equipmentId;
    private String managementNumber;
    private String alias;
    
    private BigDecimal totalVolume;
    private BigDecimal deltaH; // 오리피스 보정 계수
    private BigDecimal Yd;
  }
  
  @Getter
  @Builder(toBuilder = true)
  public static class GasSamplerSnapshot {
    private String equipmentId;
    private String managementNumber;
    private String alias;
    
    private BigDecimal totalVolume;
  }
  
  @Getter
  @Builder(toBuilder = true)
  public static class PitotTubeSnapshot {
    private String equipmentId;
    private String managementNumber;
    private String alias;
    private String type;
    
    private List<PitotCoefficient> coefficients;
    
    @Getter
    @Builder(toBuilder = true)
    public static class PitotCoefficient {
      private String coefficientId;
      private BigDecimal velocity;
      private BigDecimal coefficient;
    }
  }
  
  @Getter
  @Builder(toBuilder = true)
  public static class NozzleSnapshot {
    private String equipmentId;
    private String managementNumber;
    private String alias;
    
    private List<NozzleDiameter> diameters;
    
    @Getter
    @Builder(toBuilder = true)
    public static class NozzleDiameter {
      private BigDecimal diameter;
    }
  }
}
