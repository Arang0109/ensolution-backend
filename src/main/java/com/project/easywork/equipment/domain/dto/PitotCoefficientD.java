package com.project.easywork.equipment.domain.dto;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class PitotCoefficientD {
  private Long id;
  private BigDecimal velocity;
  private BigDecimal coefficient;
}