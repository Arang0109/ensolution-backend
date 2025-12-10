package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.common.util.GasCalculator;
import com.project.easywork.measurement.pipeline.domain.Measurement;
import com.project.easywork.measurement.pipeline.context.MeasurementContext;

import java.math.BigDecimal;

public class GasDensityCalculateStep implements MeasurementStep {
  
  @Override
  public void execute(MeasurementContext context) {
    
    Measurement d = context.getDomain();
    
    BigDecimal avgO2 = GasCalculator.avg(d.getMeasurement().exhaustGas().o2Concentration());
    BigDecimal avgCo2 = GasCalculator.avg(d.getMeasurement().exhaustGas().co2Concentration());
    BigDecimal avgCo = GasCalculator.avg(d.getMeasurement().exhaustGas().coConcentration());
    
    BigDecimal moisture = BigDecimal.valueOf(context.getMoistureRatio());
    
    BigDecimal gasDensity = GasCalculator.toActualDensity(
        GasCalculator.calGasDensity(avgO2, avgCo2, avgCo, moisture),
        BigDecimal.valueOf(d.getMeasurement().exhaustGas().gasTemperature()),
        BigDecimal.valueOf(context.getAtmospherePressure() + context.getStaticPressure())
    );
    
    context.setGasDensity(gasDensity.doubleValue());
    
    // 산소 보정
    context.setOxygenCorrected(1.0);
    
    Double standardO2 = d.getMeasurement().exhaustGas().standardOxygen();
    
    if (standardO2 != null && standardO2 > 0) {
      double corrected = (21.0 - standardO2) / (21.0 - avgO2.doubleValue());
      context.setOxygenCorrected(corrected);
    }
  }
}
