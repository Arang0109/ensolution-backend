package com.project.easywork.measurement.dto.document.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDoc {
  
  private ParticularEquipmentDoc particularEquipment;
  private PitotTubeDoc pitotTube;
  
  public void merge(EquipmentDoc doc) {
    if (doc == null) return;
    if (doc.particularEquipment != null) {
      this.particularEquipment = doc.particularEquipment;
    }
    if (doc.pitotTube != null) {
      this.pitotTube = doc.pitotTube;
    }
  }
  
  public void changeParticularEquipment(
      ParticularEquipmentDoc doc
  ) {
    this.particularEquipment = doc;
  }
  
  public void changePitotTube(
      PitotTubeDoc doc
  ) {
    this.pitotTube = doc;
  }
  
  @Getter
  @Builder
  public static class ParticularEquipmentDoc {
    private Long particularEquipmentId;
    private String modelName;
    private String equipmentName;
    
    private BigDecimal deltaH; // 오리피스 보정 계수
    private BigDecimal Yd;
  }
  
  @Getter
  @Builder
  public static class PitotTubeDoc {
    private Long pitotTubeId;
    private String modelName;
    private String equipmentName;
    
    private List<CoefficientDoc> coefficients;
    
    @Getter
    @Builder
    public static class CoefficientDoc {
      private Long coefficientId;
      private BigDecimal velocity;
      private BigDecimal coefficient;
    }
  }
}
