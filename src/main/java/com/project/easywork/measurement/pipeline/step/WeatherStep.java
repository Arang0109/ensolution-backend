package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.measurement.pipeline.context.MeasurementContext;

public class WeatherStep implements MeasurementStep {
  
  /**
   * 1. 대기압 변환 로직 ( to mmHg )
   */
  @Override
  public void execute(MeasurementContext context) {
//    Measurement domain = context.getDomain();
//
//    MeasurementDoc measurement = domain.getMeasurement();
//    WeatherDoc weather = measurement.getWeather();
//
//    BigDecimal convertedAtmosP = PressureConverter.toMmHg(
//        domain.getMeasurement().getWeather().getPressure().getPressure(),
//        PressureUnit.from(domain.getMeasurement().getWeather().getPressure().getUnit())
//    ).setScale(1, RoundingMode.HALF_UP);
//
//    context.setConvertedAtmosP(convertedAtmosP);
//
//    WeatherDoc updatedWeather = weather.toBuilder()
//        .convertedPressure(convertedAtmosP)
//        .build();
//
//    MeasurementDoc updatedMeasurement = measurement.toBuilder()
//        .weather(updatedWeather)
//        .build();
//
//    domain.updateMeasurement(updatedMeasurement);
  }
}