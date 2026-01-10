package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.common.util.GasCalculator;
import com.project.easywork.measurement.pipeline.domain.Measurement;
import com.project.easywork.measurement.pipeline.context.MeasurementContext;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoistureCalculateStep implements MeasurementStep {
  
  @Override
  public void execute(MeasurementContext context) {
    
    Measurement d = context.getDomain();
    
    // 흡수병 무게 차이
    BigDecimal diffWeight =
        d.getMeasurement().moisture().weight().after()
            .subtract(d.getMeasurement().moisture().weight().before());
    
    // 부피 차이
    BigDecimal diffVolume =
        d.getMeasurement().moisture().dryGasVolume().after()
            .subtract(d.getMeasurement().moisture().dryGasVolume().before());
    
    // 평균 온도
    BigDecimal avgTemp =
        d.getMeasurement().moisture().gasMeterTemperature().in()
            .add(d.getMeasurement().moisture().gasMeterTemperature().out())
            .divide(BigDecimal.valueOf(2), 6, RoundingMode.HALF_UP);
    
    // 압력 = 대기압 + 게이지압
    BigDecimal pressure =
        context.getAtmospherePressure()
            .add(context.getGasMeterGaugePressure());
    
    // 표준 가스 부피
    BigDecimal standardGasVolume =
        GasCalculator.toActualDensity(diffVolume, avgTemp, pressure);
    
    // 무게 → 부피 변환
    BigDecimal diffWeightToVolume =
        diffWeight
            .multiply(BigDecimal.valueOf(22.4))
            .divide(BigDecimal.valueOf(18), 10, RoundingMode.HALF_UP);
    
    // 분모
    BigDecimal denominator =
        standardGasVolume.add(diffWeightToVolume);
    
    // 수분 함량(%)
    BigDecimal moistureContent =
        diffWeightToVolume
            .divide(denominator, 6, RoundingMode.HALF_UP)
            .multiply(BigDecimal.valueOf(100));
    
    context.setMoistureRatio(moistureContent);
  }
}