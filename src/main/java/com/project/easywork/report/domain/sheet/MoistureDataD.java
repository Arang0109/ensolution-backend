package com.project.easywork.report.domain.sheet;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MoistureDataD {
  private BigDecimal beforeW;
  private BigDecimal afterW;
  private BigDecimal beforeV;
  private BigDecimal afterV;
  private BigDecimal inTemp;
  private BigDecimal outTemp;
  
  BigDecimal suctionVelocity;
  BigDecimal gasMeterGaugePressure;
}
