package com.project.easywork.report.domain.equipment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDataD {
  private BigDecimal deltaH;
  private BigDecimal yd;
}