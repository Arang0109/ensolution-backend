package com.project.easywork.report.domain.sheet;

import com.project.easywork.plan.domain.MeasurementCategory;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SheetDataD {
  private MeasurementCategory category; // 측정 카테고리 (먼지, 중금속, 수은, PM10, PM2.5, 기타)
  
  private WeatherDataD weather;
  private MoistureDataD moisture;
  private ExhaustGasDataD exhaustGas;
  private List<MeasurementPointDataD> measurementPoints;
  
  private ParticleSampleD particleSample;
  
  private BigDecimal Cp;
  private BigDecimal nozzleSize;
}
