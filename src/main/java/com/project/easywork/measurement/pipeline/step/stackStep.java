package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.common.constant.Shape;
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
    double x, y;
    int measurePoint;
    
    x = d.getMeasurement().preInfo().stack().horizontalLength();
    
    if (stackShape.equals(Shape.RECTANGULAR)) {
      y = d.getMeasurement().preInfo().stack().verticalLength();
      MeasurePointStrategy strategy = MeasurePointStrategyFactory.of("rectangle");
      measurePoint = strategy.calculate(x, y);
    } else if (stackShape.equals(Shape.CIRCULAR)) {
      y = d.getMeasurement().preInfo().stack().horizontalLength();
      MeasurePointStrategy strategy = MeasurePointStrategyFactory.of("circle");
      measurePoint = strategy.calculate(x, y);
      
      int n = 1;
      
      if (measurePoint != 1) {
        n = measurePoint / 4;
      }
      
      List<Double> list = new ArrayList<Double>();
      
      for (int i = 0; i < n; i++) {
        double k = (x / 2.0) * Math.sqrt((2.0*(i+1) - 1) / (2.0*n));
        list.add((x / 2.0) - k);
      }
    }
    
    
  }
}
