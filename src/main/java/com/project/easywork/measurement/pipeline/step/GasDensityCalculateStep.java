package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.common.util.GasCalculator;
import com.project.easywork.measurement.pipeline.domain.Measurement;
import com.project.easywork.measurement.pipeline.context.MeasurementContext;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GasDensityCalculateStep implements MeasurementStep {
  
  private static final BigDecimal TWENTY_ONE = new BigDecimal("21.0");
  private static final BigDecimal TWENTY_POINT_NINE = new BigDecimal("20.9");
  private static final BigDecimal ONE = BigDecimal.ONE;
  
  @Override
  public void execute(MeasurementContext context) {
    
    Measurement d = context.getDomain();
    
    // 평균 농도
    BigDecimal avgO2  = GasCalculator.avg(d.getMeasurement().exhaustGas().o2Concentration());
    BigDecimal avgCo2 = GasCalculator.avg(d.getMeasurement().exhaustGas().co2Concentration());
    BigDecimal avgCo  = GasCalculator.avg(d.getMeasurement().exhaustGas().coConcentration());
    
    // 수분량
    BigDecimal moisture = context.getMoistureRatio();

    // 가스 밀도 계산부
    BigDecimal gasDensity = GasCalculator.calGasDensity(avgO2, avgCo2, avgCo, moisture);
    context.setGasDensity(gasDensity);
    
    // ------------------------
    // 산소 보정 계산
    // ------------------------
    
    // 평균 산소농도 반올림 (소수 1자리)
    BigDecimal avgO2Rounded = avgO2.setScale(1, RoundingMode.HALF_UP);
    
    // 기본값 1.0
    context.setOxygenCorrected(BigDecimal.ONE);
    
    BigDecimal standardO2 = d.getMeasurement().client().stack().standardOxygen();
    
    if (standardO2 != null && standardO2.compareTo(BigDecimal.ZERO) > 0) {
      
      // rule 1: 기준산소 >= 20.9 → 무조건 1
      if (standardO2.compareTo(TWENTY_POINT_NINE) >= 0) {
        context.setOxygenCorrected(ONE);
        return;
      }
      
      // rule 2: (21 - 기준산소) / (21 - 평균산소)
      BigDecimal numerator = TWENTY_ONE.subtract(standardO2);
      BigDecimal denominator = TWENTY_ONE.subtract(avgO2Rounded);
      
      BigDecimal corrected = numerator.divide(denominator, 6, RoundingMode.HALF_UP);
      
      context.setOxygenCorrected(corrected);
    }
  }
}