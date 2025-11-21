package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.common.util.Calculator;
import com.project.easywork.measurement.domain.Measurement;
import com.project.easywork.measurement.pipeline.context.MeasurementContext;

public class MoistureCalculateStep implements MeasurementStep {
  
  @Override
  public void execute(MeasurementContext context) {
    
    Measurement d = context.getDomain();
    
    double diffWeight = d.getMeasurement().moisture().weight().after() - d.getMeasurement().moisture().weight().before();
    double diffVolume = d.getMeasurement().moisture().dryGasVolume().after() - d.getMeasurement().moisture().dryGasVolume().before();
    double avgTemp = (d.getMeasurement().moisture().gasMeterTemperature().in() + d.getMeasurement().moisture().gasMeterTemperature().out()) / 2.0;
    
    double pressure = context.getWeatherPressureMmHg() + context.getGasEquipGaugePressureMmHg();
    
    double standardGasVolume = Calculator.toActualDensity(
        diffVolume,
        avgTemp,
        pressure
    );
    
    double diffWeightToVolume = (22.4/18)*diffWeight;
    
    double moistureContent = (diffWeightToVolume / (standardGasVolume + diffWeightToVolume)) * 100;
    
    context.setMoistureContent(moistureContent);
  }
}