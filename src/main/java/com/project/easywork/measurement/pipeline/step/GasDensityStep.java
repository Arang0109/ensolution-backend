package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.common.domain.PressureUnit;
import com.project.easywork.common.util.PressureConverter;
import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.measurement.dto.document.input.MeasurementPointDoc;
import com.project.easywork.measurement.pipeline.context.MeasurementContext;
import com.project.easywork.measurement.pipeline.domain.Measurement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class GasDensityStep implements MeasurementStep {
  
  private static final BigDecimal STD_TEMPERATURE = new BigDecimal("273.0");
  private static final BigDecimal STD_PRESSURE = new BigDecimal("760.0");
  private static final BigDecimal PITOT_TUBE = new BigDecimal("8.40");
  
  @Override
  public void execute(MeasurementContext context) {
    Measurement domain = context.getDomain();
    
    MeasurementDoc measurement = domain.getMeasurement();
    BigDecimal convertedAtomsP = PressureConverter.toMmHg(
        domain.getMeasurement().getWeather().getPressure().getPressure(),
        PressureUnit.from(domain.getMeasurement().getWeather().getPressure().getUnit())
    );
    BigDecimal stdGasDensity = measurement.getExhaustGas().getGasDensity();
    List<MeasurementPointDoc> measurementPoints = measurement.getMeasurementPoints();
    
    List<MeasurementPointDoc> updatedPoints = new ArrayList<>();
    
    for (MeasurementPointDoc doc : measurementPoints) {
      BigDecimal Ts = doc.getGasTemperature();
      BigDecimal Ps = doc.getDynamicPressure();
      BigDecimal convertedPs = PressureConverter.toMmHg(
          doc.getStaticPressure(), PressureUnit.MMH2O
      );
      
      BigDecimal tempFactor =
          STD_TEMPERATURE.divide(STD_TEMPERATURE.add(Ts), 10, RoundingMode.HALF_UP);
      
      BigDecimal pressureFactor =
          convertedAtomsP.add(convertedPs)
              .divide(STD_PRESSURE, 10, RoundingMode.HALF_UP);
      
      BigDecimal gasDensity =
          stdGasDensity
              .multiply(tempFactor)
              .multiply(pressureFactor);
      
      BigDecimal inside =
          Ps.multiply(new BigDecimal("19.62"))
              .divide(gasDensity, 10, RoundingMode.HALF_UP);
      
      BigDecimal root = sqrt(inside, 10);
      
      BigDecimal gasVelocity =
          PITOT_TUBE
              .multiply(root)
              .setScale(2, RoundingMode.HALF_UP);
      
      
      MeasurementPointDoc updated = doc.toBuilder()
          .gasDensity(gasDensity)
          .gasVelocity(gasVelocity)
          .build();
      
      updatedPoints.add(updated);
    }
    
    MeasurementDoc updatedMeasurement = measurement.toBuilder()
        .measurementPoints(updatedPoints)
        .build();
    
    domain.updateMeasurement(updatedMeasurement);
  }
  
  public static BigDecimal sqrt(BigDecimal value, int scale) {
    BigDecimal two = BigDecimal.valueOf(2);
    BigDecimal x0 = BigDecimal.ZERO;
    BigDecimal x1 = new BigDecimal(Math.sqrt(value.doubleValue()));
    
    while (!x0.equals(x1)) {
      x0 = x1;
      x1 = value.divide(x0, scale, RoundingMode.HALF_UP);
      x1 = x1.add(x0);
      x1 = x1.divide(two, scale, RoundingMode.HALF_UP);
    }
    return x1;
  }
}
