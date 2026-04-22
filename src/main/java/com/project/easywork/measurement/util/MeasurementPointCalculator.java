package com.project.easywork.measurement.util;

import com.project.easywork.client.domain.Shape;
import com.project.easywork.common.util.measurePoint.MeasurePointStrategy;
import com.project.easywork.common.util.measurePoint.MeasurePointStrategyFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MeasurementPointCalculator {
  public int calculate(Shape stackShape, BigDecimal horizontal, BigDecimal vertical) {
    
    // Draft 단계 기본값
    if (stackShape == null || stackShape == Shape.OTHER) {
      return 1;
    }
    
    MeasurePointStrategy strategy;
    
    switch (stackShape) {
      case RECTANGULAR -> {
        if (horizontal == null || vertical == null) {
          return 1;
        }
        
//        strategy = MeasurePointStrategyFactory.of("rectangular");
//        int point = strategy.calculate(horizontal, vertical);
//
//        // 간소화 규칙
//        return point == 1 ? 1 : (int) Math.ceil(point / 4.0);
        return 1; // 임시
      }
      
      case CIRCULAR -> {
        if (horizontal == null) {
          return 1;
        }
        
        strategy = MeasurePointStrategyFactory.of("circular");
        int point = strategy.calculate(horizontal);
        
        // 간소화 규칙 적용
        return point == 1 ? 1 : (int) Math.ceil(point / 4.0);
      }
      
      default -> {
        return 1;
      }
    }
  }
}
