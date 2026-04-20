package com.project.easywork.report.domain.sheet;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MeasurementPointDataD {
  private BigDecimal Ts;        // 배출가스 온도
  private BigDecimal Pv;       // 동압
  private BigDecimal Ps;
  
  private BigDecimal inTemp;
  private BigDecimal outTemp;
  private BigDecimal beforeV;
  private BigDecimal afterV;
  private BigDecimal samplingTime;
  private BigDecimal vacuumGaugePressure;
  private BigDecimal finalImpingerTemperature;
}
