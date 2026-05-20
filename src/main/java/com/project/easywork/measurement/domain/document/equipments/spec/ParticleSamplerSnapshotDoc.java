package com.project.easywork.measurement.domain.document.equipments.spec;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder(toBuilder = true)
public class ParticleSamplerSnapshotDoc {
  private String equipmentId;
  private String managementNumber;
  private String alias;
  
  private BigDecimal totalVolume;
  private BigDecimal deltaH; // 오리피스 보정 계수
  private BigDecimal yd;
}