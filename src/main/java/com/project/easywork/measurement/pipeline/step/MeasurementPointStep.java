package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.client.domain.Shape;
import com.project.easywork.common.exception.CustomException;
import com.project.easywork.common.exception.ErrorCode;
import com.project.easywork.common.util.measurePoint.MeasurePointStrategy;
import com.project.easywork.common.util.measurePoint.MeasurePointStrategyFactory;
import com.project.easywork.measurement.pipeline.context.MeasurementContext;
import com.project.easywork.measurement.pipeline.domain.Measurement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class MeasurementPointStep implements MeasurementStep {
  
  private static final BigDecimal TWO = BigDecimal.valueOf(2);
  
  @Override
  public void execute(MeasurementContext context) {
    Measurement d = context.getDomain();
    
    Shape stackShape = d.getMeasurement().client().stack().shape();
    BigDecimal diameter = d.getMeasurement().client().stack().horizontalLength();
    BigDecimal diameter2   = d.getMeasurement().client().stack().verticalLength();
    
    boolean simplifiedMeasurement = d.getMeasurement().preInfo().simplifiedMeasurement();
    
    MeasurePointStrategy strategy;
    
    switch (stackShape) {
      case RECTANGULAR -> {
        strategy = MeasurePointStrategyFactory.of("rectangle");
        int pointCount = strategy.calculate(diameter, diameter2);
        context.setMeasurementPointCnt(pointCount);
        context.setCircularAxisCoords(null);
      }
      
      case CIRCULAR -> {
        strategy = MeasurePointStrategyFactory.of("circular");
        int pointCount = strategy.calculate(diameter);
        context.setMeasurementPointCnt(pointCount);
        
        int n = simplifiedMeasurement
            ? (pointCount == 1 ? 1 : pointCount / 4)
            : pointCount;
        
        List<BigDecimal> coords = new ArrayList<>();
        BigDecimal r = diameter.divide(TWO, 6, RoundingMode.HALF_UP);
        
        for (int i = 0; i < n; i++) {
          // (2i + 1) / (2n)
          BigDecimal numerator = BigDecimal.valueOf(2L * i + 1);
          BigDecimal denominator = BigDecimal.valueOf(2L * n);
          
          BigDecimal ratio = numerator.divide(denominator, 10, RoundingMode.HALF_UP);
          
          // sqrt는 double로 계산 → BigDecimal 복귀
          BigDecimal k = r.multiply(
              BigDecimal.valueOf(Math.sqrt(ratio.doubleValue()))
          );
          
          BigDecimal coord = r.subtract(k);
          coords.add(coord);
        }
        
        context.setCircularAxisCoords(coords);
      }
      
      default -> throw new CustomException(ErrorCode.BAD_REQUEST, "Unsupported stack shape" + stackShape);
    }
  }
}