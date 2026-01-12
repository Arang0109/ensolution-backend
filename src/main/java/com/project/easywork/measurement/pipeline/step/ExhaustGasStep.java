package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.common.util.GasCalculator;
import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.measurement.dto.document.input.ExhaustGasDoc;
import com.project.easywork.measurement.pipeline.domain.Measurement;
import com.project.easywork.measurement.pipeline.context.MeasurementContext;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExhaustGasStep implements MeasurementStep {
  
  private static final BigDecimal TWENTY_ONE = new BigDecimal("21.0");
  private static final BigDecimal TWENTY_POINT_NINE = new BigDecimal("20.9");
  private static final BigDecimal ONE = BigDecimal.ONE;
  
  @Override
  public void execute(MeasurementContext context) {
    Measurement domain = context.getDomain();
    MeasurementDoc measurement = domain.getMeasurement();
    
    ExhaustGasDoc exhaustGas = measurement.getExhaustGas();
    
    BigDecimal avgO2  = GasCalculator.avg(exhaustGas.getO2Concentration());
    BigDecimal avgCo2 = GasCalculator.avg(exhaustGas.getCo2Concentration());
    BigDecimal avgCo  = GasCalculator.avg(exhaustGas.getCoConcentration());
    
    BigDecimal moistureRatio = measurement.getMoisture().getMoistureRatio();
    BigDecimal gasDensity = GasCalculator.calGasDensity(avgO2, avgCo2, avgCo, moistureRatio);
    
    BigDecimal avgO2Rounded = avgO2.setScale(1, RoundingMode.HALF_UP);
    BigDecimal corrected = ONE;
    
    BigDecimal standardO2 = measurement.getClient().getStack().getStandardOxygen();
    
    if (standardO2 != null && standardO2.compareTo(BigDecimal.ZERO) > 0) {
      if (standardO2.compareTo(TWENTY_POINT_NINE) < 0) {
        BigDecimal numerator = TWENTY_ONE.subtract(standardO2);
        BigDecimal denominator = TWENTY_ONE.subtract(avgO2Rounded);
        
        if (denominator.compareTo(BigDecimal.ZERO) > 0) {
          corrected = numerator.divide(denominator, 6, RoundingMode.HALF_UP);
        }
      }
    }
    
    System.out.println("applyResult 진입 전");
    
    applyResult(domain, measurement, exhaustGas, gasDensity, corrected);
  }
  
  private void applyResult(
      Measurement domain,
      MeasurementDoc measurement,
      ExhaustGasDoc exhaustGas,
      BigDecimal gasDensity,
      BigDecimal o2CorrectionFactor
  ) {
    System.out.println("applyResult 진입");
    System.out.println("gasDensity: " + gasDensity);
    ExhaustGasDoc updated = exhaustGas.toBuilder()
        .gasDensity(gasDensity)
        .o2CorrectionFactor(o2CorrectionFactor)
        .build();
    
    MeasurementDoc updatedMeasurement = measurement.toBuilder()
        .exhaustGas(updated)
        .build();
    
    domain.updateMeasurement(updatedMeasurement);
  }
}