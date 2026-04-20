package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.measurement.pipeline.SheetContext;
import com.project.easywork.measurement.util.Calculator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@Order(5)
@RequiredArgsConstructor
public class DensityStep implements SheetStep {
  
  private final Calculator calculator;
  
  private final BigDecimal SM = BigDecimal.valueOf(22.4);
  private final BigDecimal O2_MOL = BigDecimal.valueOf(0.32);
  private final BigDecimal CO2_MOL = BigDecimal.valueOf(0.44);
  private final BigDecimal CO_MOL = BigDecimal.valueOf(0.28);
  private final BigDecimal N2_MOL = BigDecimal.valueOf(0.28);
  
  @Override
  public void execute(SheetContext context) {
    BigDecimal o2 = context.getO2();
    BigDecimal co2 = context.getCo2();
    BigDecimal co = context.getCo();
    BigDecimal n2 = context.getN2();
    BigDecimal Xw = context.getXw();
    
    BigDecimal avgTg = context.getAvgTg();
    BigDecimal Pg = context.getPg();
    
    BigDecimal standardGasDensity = calcStandardGasDensity(o2, co2, co, n2, Xw);
    BigDecimal gasDensity = convertFromSTP(standardGasDensity, avgTg, Pg);
    
    context.setStandardGasDensity(calculator.round(standardGasDensity, 2));
    context.setGasDensity(calculator.round(gasDensity, 3));
  }
  
  private BigDecimal convertFromSTP(BigDecimal value, BigDecimal temperature, BigDecimal pressure) {
    BigDecimal t = BigDecimal.valueOf(273);
    BigDecimal p = BigDecimal.valueOf(760);
    
    return value
        .multiply(t.divide(temperature, 5, RoundingMode.HALF_UP))
        .multiply(pressure.divide(p, 5, RoundingMode.HALF_UP));
  }
  
  private BigDecimal calcStandardGasDensity(
      BigDecimal o2Avg, BigDecimal co2Avg, BigDecimal coAvg, BigDecimal n2Avg, BigDecimal Xw) {
    BigDecimal o2 = o2Avg.divide(SM, 5, RoundingMode.HALF_UP).multiply(O2_MOL);
    BigDecimal co2 = co2Avg.divide(SM, 5, RoundingMode.HALF_UP).multiply(CO2_MOL);
    BigDecimal co = coAvg.divide(SM, 5, RoundingMode.HALF_UP).multiply(CO_MOL);
    BigDecimal n2 = n2Avg.divide(SM, 5, RoundingMode.HALF_UP).multiply(N2_MOL);
    
    BigDecimal dryGasDensity = o2.add(co2).add(co).add(n2);
    BigDecimal moisture = BigDecimal.valueOf(18)
        .multiply(Xw)
        .divide(SM, 5, RoundingMode.HALF_UP)
        .divide(BigDecimal.valueOf(100), 5, RoundingMode.HALF_UP);
    
    return dryGasDensity
        .multiply(BigDecimal.valueOf(100).subtract(Xw))
        .divide(BigDecimal.valueOf(100), 5, RoundingMode.HALF_UP)
        .add(moisture);
  }
}
