package com.project.easywork.report.domain.sheet;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExhaustGasDataD {
  private List<BigDecimal> o2Concentration;
  private List<BigDecimal> co2Concentration;
  private List<BigDecimal> coConcentration;
  private List<BigDecimal> noxConcentration;
  private List<BigDecimal> soxConcentration;
}
