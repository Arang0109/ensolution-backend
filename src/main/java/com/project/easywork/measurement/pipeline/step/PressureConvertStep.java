package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.common.util.PressureConverter;
import com.project.easywork.common.domain.PressureUnit;
import com.project.easywork.measurement.pipeline.domain.Measurement;
import com.project.easywork.measurement.pipeline.context.MeasurementContext;

public class PressureConvertStep implements MeasurementStep {
  @Override
  public void execute(MeasurementContext context) {
    Measurement d = context.getDomain();
    
    Double weather = PressureConverter.toMmHg(
        d.getMeasurement().weather().pressure().pressure(),
        PressureUnit.from(d.getMeasurement().weather().pressure().unit())
    );
    
    Double staticPressure = PressureConverter.toMmHg(
        d.getMeasurement().exhaustGas().staticPressure().pressure(),
        PressureUnit.from(d.getMeasurement().exhaustGas().staticPressure().unit())
    );
    
    Double dynamicPressure = PressureConverter.toMmHg(
        d.getMeasurement().exhaustGas().dynamicPressure().pressure(),
        PressureUnit.from(d.getMeasurement().exhaustGas().dynamicPressure().unit())
    );
    
    Double gasEquipGaugePressure = PressureConverter.toMmHg(
        d.getMeasurement().moisture().gasMeterGaugePressure(),
        PressureUnit.MMH2O
    );
    
    context.setAtmospherePressure(weather);
    context.setStaticPressure(staticPressure);
    context.setDynamicPressure(dynamicPressure);
    context.setGasMeterGaugePressure(gasEquipGaugePressure);
  }
}