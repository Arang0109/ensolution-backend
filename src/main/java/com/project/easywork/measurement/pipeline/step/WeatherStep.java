package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.common.util.PressureConverter;
import com.project.easywork.common.domain.PressureUnit;
import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.measurement.dto.document.input.WeatherDoc;
import com.project.easywork.measurement.pipeline.domain.Measurement;
import com.project.easywork.measurement.pipeline.context.MeasurementContext;

import java.math.BigDecimal;

public class WeatherStep implements MeasurementStep {
  
  /**
   * 1. 대기압 변환 로직 ( to mmHg )
   */
  @Override
  public void execute(MeasurementContext context) {
    Measurement domain = context.getDomain();
    
    MeasurementDoc measurement = domain.getMeasurement();
    WeatherDoc weather = measurement.getWeather();
    
    BigDecimal convertedAtmosP = PressureConverter.toMmHg(
        domain.getMeasurement().getWeather().getPressure().getPressure(),
        PressureUnit.from(domain.getMeasurement().getWeather().getPressure().getUnit())
    );
    
    WeatherDoc updatedWeather = weather.toBuilder()
        .convertedPressure(convertedAtmosP)
        .build();
    
    MeasurementDoc updatedMeasurement = measurement.toBuilder()
        .weather(updatedWeather)
        .build();
    
    domain.updateMeasurement(updatedMeasurement);
  }
}