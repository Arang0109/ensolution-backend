package com.project.easywork.equipment.domain.document.spec;

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
public class PitotTubeSpecDoc {
  private PitotTubeType type;
  private List<CoefficientDoc> coefficients;
  
  @Getter
  @Builder(toBuilder = true)
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CoefficientDoc {
    private BigDecimal coefficient;
    private BigDecimal velocity;
  }
}