package com.project.easywork.equipment.domain.document.spec;

import com.project.easywork.equipment.domain.EquipType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ParticleSamplerSpec extends EquipmentSpec {
  private BigDecimal totalVolume;
  private BigDecimal orificeDp;
  private BigDecimal yd;
  
  @Override
  public EquipType getType() {
    return EquipType.PARTICLE_SAMPLER;
  }
}