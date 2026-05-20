package com.project.easywork.measurement.domain.document.equipments.spec;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder(toBuilder = true)
public class NozzleSnapshotDoc {
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
