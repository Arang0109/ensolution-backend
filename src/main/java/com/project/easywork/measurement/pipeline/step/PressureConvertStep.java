package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.common.util.PressureConverter;
import com.project.easywork.common.domain.PressureUnit;
import com.project.easywork.measurement.pipeline.domain.Measurement;
import com.project.easywork.measurement.pipeline.context.MeasurementContext;

import java.math.BigDecimal;

public class PressureConvertStep implements MeasurementStep {
  @Override
  public void execute(MeasurementContext context) {
    Measurement d = context.getDomain();
    
    BigDecimal atmospherePressure = PressureConverter.toMmHg(
        d.getMeasurement().weather().pressure().pressure(),
        PressureUnit.from(d.getMeasurement().weather().pressure().unit())
    );
    
    BigDecimal gasEquipGaugePressure = PressureConverter.toMmHg(
        d.getMeasurement().moisture().gasMeterGaugePressure(),
        PressureUnit.MMH2O
    );
    
    context.setAtmospherePressure(atmospherePressure);
    context.setGasMeterGaugePressure(gasEquipGaugePressure);
  }
}