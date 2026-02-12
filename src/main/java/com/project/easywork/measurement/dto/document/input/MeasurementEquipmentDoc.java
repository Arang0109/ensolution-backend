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
  
  private ParticleSamplerDoc particleSampler;
  private GasSamplerDoc gasSampler;
  private PitotTubeDoc pitotTube;
  private NozzleDoc nozzle;
  
  @Getter
  @Builder(toBuilder = true)
  public static class ParticleSamplerDoc {
    private String equipmentId;
    private String managementNumber;
    private String alias;
    
    private BigDecimal totalVolume;
    private BigDecimal deltaH; // 오리피스 보정 계수
    private BigDecimal yd;
  }
  
  @Getter
  @Builder(toBuilder = true)
  public static class GasSamplerDoc {
    private String equipmentId;
    private String managementNumber;
    private String alias;
    
    private BigDecimal totalVolume;
  }
  
  @Getter
  @Builder(toBuilder = true)
  public static class PitotTubeDoc {
    private String equipmentId;
    private String managementNumber;
    private String alias;
    private String pitotTubeType;
    
    private List<PitotCoefficient> coefficients;
    
    @Getter
    @Builder(toBuilder = true)
    public static class PitotCoefficient {
      private BigDecimal velocity;
      private BigDecimal coefficient;
    }
  }
  
  @Getter
  @Builder(toBuilder = true)
  public static class NozzleDoc {
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
