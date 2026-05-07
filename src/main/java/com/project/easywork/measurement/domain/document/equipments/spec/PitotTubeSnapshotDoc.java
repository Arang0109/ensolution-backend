package com.project.easywork.measurement.domain.document.equipments.spec;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder(toBuilder = true)
public class PitotTubeSnapshotDoc {
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