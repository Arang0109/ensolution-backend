package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.common.util.pressure.PressureConverter;
import com.project.easywork.measurement.pipeline.domain.Measurement;
import com.project.easywork.measurement.pipeline.context.MeasurementContext;

public class PressureConvertStep implements MeasurementStep {
  @Override
  public void execute(MeasurementContext context) {
    Measurement d = context.getDomain();
    
    Double weather = PressureConverter.toMmHg(
        d.getMeasurement().weather().pressure().pressure(),
        d.getMeasurement().weather().pressure().unit()
    );
    
    Double staticPressure = PressureConverter.toMmHg(
        d.getMeasurement().exhaustGas().staticPressure().pressure(),
        d.getMeasurement().exhaustGas().staticPressure().unit()
    );
    
    Double gasEquipGaugePressure = PressureConverter.toMmHg(
        d.getMeasurement().moisture().gasMeterGaugePressure(),
        "mmH2o"
    );
    
    context.setWeatherPressureMmHg(weather);
    context.setStaticPressureMmHg(staticPressure);
    context.setGasEquipGaugePressureMmHg(gasEquipGaugePressure);
  }
}