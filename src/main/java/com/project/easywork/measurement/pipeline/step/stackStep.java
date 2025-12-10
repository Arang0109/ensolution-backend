package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.client.domain.Shape;
import com.project.easywork.common.util.measurePoint.MeasurePointStrategy;
import com.project.easywork.common.util.measurePoint.MeasurePointStrategyFactory;
import com.project.easywork.measurement.pipeline.domain.Measurement;
import com.project.easywork.measurement.pipeline.context.MeasurementContext;

import java.util.ArrayList;
import java.util.List;

public class stackStep implements MeasurementStep {
  @Override
  public void execute(MeasurementContext context) {
    
    Measurement d = context.getDomain();
    
    Shape stackShape = d.getMeasurement().preInfo().stack().shape();
    
    double diameter = d.getMeasurement().preInfo().stack().horizontalLength();
    double height   = d.getMeasurement().preInfo().stack().verticalLength();
    
    switch (stackShape) {
      case Shape.RECTANGULAR -> {
        MeasurePointStrategy strategy = MeasurePointStrategyFactory.of("rectangle");
        int pointCount = strategy.calculate(diameter, height);
        context.setPointCount(pointCount);
      }
      case Shape.CIRCULAR -> {
        MeasurePointStrategy strategy = MeasurePointStrategyFactory.of("circle");
        int pointCount = strategy.calculate(diameter, diameter);
        
        int n = (pointCount == 1) ? 1 : pointCount / 4;
        
        List<Double> coords = new ArrayList<>();
        double r = diameter / 2.0;
        
        for (int i = 0; i < n; i++) {
          double k = r * Math.sqrt((2.0 * (i+1) - 1) / (2.0 * n));
          coords.add(r - k);
        }
        context.setPointCount(pointCount);
      }
    }
  }
}
