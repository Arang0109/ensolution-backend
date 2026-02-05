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
public class GasSamplerSpec extends EquipmentSpec {
  private BigDecimal totalVolume;
  
  @Override
  public EquipType getType() {
    return EquipType.GAS_SAMPLER;
  }
}