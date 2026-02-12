package com.project.easywork.equipment.domain.document.spec;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ParticleSamplerSpec implements EquipmentSpec {
  private BigDecimal totalVolume;
  private BigDecimal orificeDp;
  private BigDecimal yd;
}