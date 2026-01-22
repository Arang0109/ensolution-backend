package com.project.easywork.equipment.domain.document.spec;

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
public class NozzleSpecDoc {
  private List<NozzleDiameterSpecDoc> nozzleDiameters;
  
  @Getter
  @Builder(toBuilder = true)
  @NoArgsConstructor
  @AllArgsConstructor
  public static class NozzleDiameterSpecDoc {
    private BigDecimal diameter;
  }
}