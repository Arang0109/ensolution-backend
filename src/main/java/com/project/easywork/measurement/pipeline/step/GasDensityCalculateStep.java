package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.common.util.Calculator;
import com.project.easywork.measurement.pipeline.domain.Measurement;
import com.project.easywork.measurement.pipeline.context.MeasurementContext;

public class GasDensityCalculateStep implements MeasurementStep {
  
  @Override
  public void execute(MeasurementContext context) {
    
    Measurement d = context.getDomain();
    
    Double avgO2 = Calculator.avg(d.getMeasurement().exhaustGas().o2Concentration());
    Double avgCo2 = Calculator.avg(d.getMeasurement().exhaustGas().co2Concentration());
    Double avgCo = Calculator.avg(d.getMeasurement().exhaustGas().coConcentration());
    
    context.setGasDensity(
        Calculator.toActualDensity(
            Calculator.calGasDensity(avgO2, avgCo2, avgCo, context.getMoistureContent()),
            d.getMeasurement().exhaustGas().gasTemperature(),
            context.getWeatherPressureMmHg() + context.getStaticPressureMmHg()
        )
    );
    
    // 산소보정 적용
    // 기본 = 1.0 (보정 없음)
    context.setOxygenCorrected(1.0);
    
    Double standardO2 = d.getMeasurement().exhaustGas().standardOxygen();
    
    // standardO2가 null이 아니고, 보정해야 하는 경우만 --> 표준산소농도(%)가 존재하는 경우
    if (standardO2 != null && standardO2 > 0) {
      context.setOxygenCorrected(
          (21.0 - standardO2) / (21.0 - avgO2)
      );
    }
  }
}
