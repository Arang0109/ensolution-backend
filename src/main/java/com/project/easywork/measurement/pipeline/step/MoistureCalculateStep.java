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
    
    BigDecimal diffWeight = BigDecimal.valueOf(
        d.getMeasurement().moisture().weight().after()
            - d.getMeasurement().moisture().weight().before()
    );
    
    BigDecimal diffVolume = BigDecimal.valueOf(
        d.getMeasurement().moisture().dryGasVolume().after()
            - d.getMeasurement().moisture().dryGasVolume().before()
    );
    
    BigDecimal avgTemp = BigDecimal.valueOf(
        (d.getMeasurement().moisture().gasMeterTemperature().in()
            + d.getMeasurement().moisture().gasMeterTemperature().out()) / 2.0
    );
    
    BigDecimal pressure = BigDecimal.valueOf(context.getAtmospherePressure())
        .add(BigDecimal.valueOf(context.getGasMeterGaugePressure()));
    
    BigDecimal standardGasVolume = GasCalculator.toActualDensity(
        diffVolume, avgTemp, pressure
    );
    
    BigDecimal diffWeightToVolume = diffWeight
        .multiply(BigDecimal.valueOf(22.4))
        .divide(BigDecimal.valueOf(18), 10, RoundingMode.HALF_UP);
    
    BigDecimal denominator = standardGasVolume.add(diffWeightToVolume);
    
    BigDecimal moistureContent = diffWeightToVolume
        .divide(denominator, 6, RoundingMode.HALF_UP)
        .multiply(BigDecimal.valueOf(100));
    
    context.setMoistureRatio(moistureContent.doubleValue());
  }
}