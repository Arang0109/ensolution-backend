package com.project.easywork.equipment.domain.document.spec;

import com.project.easywork.equipment.domain.EquipType;
import com.project.easywork.equipment.domain.PitotTubeType;
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
public class PitotTubeSpec extends EquipmentSpec {
  private PitotTubeType pitotTubeType;
  private List<PitotCoefficientSpec> coefficients;
  
  @Override
  public EquipType getType() {
    return EquipType.PITOT_TUBE;
  }
  
  @Getter
  @Builder(toBuilder = true)
  @NoArgsConstructor
  @AllArgsConstructor
  public static class PitotCoefficientSpec {
    private BigDecimal coefficient;
    private BigDecimal velocity;
  }
}