package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.measurement.domain.document.equipments.spec.PitotTubeSnapshotDoc;
import com.project.easywork.measurement.pipeline.SheetContext;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

@Component
@Order(6)
@RequiredArgsConstructor
public class FlowStep implements SheetStep {
  
  @Override
  public void execute(SheetContext context) {
    List<PitotTubeSnapshotDoc.PitotCoefficient> pitotCoefficientList = context.getEquipment().getPitotTube().getCoefficients();
    
    BigDecimal avgPv = context.getAvgPv();
    BigDecimal gasDensity = context.getGasDensity();
    
    BigDecimal postGasVelocity = calcGasVelocity(BigDecimal.valueOf(0.84), avgPv, gasDensity);
    
    BigDecimal Cp = findPitotTubeCoefficient(pitotCoefficientList, postGasVelocity);
    
    context.setCp(Cp);
  }
  
  private BigDecimal calcGasVelocity(BigDecimal Cp, BigDecimal Pv, BigDecimal gasDensity) {
    BigDecimal value = BigDecimal.valueOf(19.62).multiply(Pv).divide(gasDensity, 5, RoundingMode.HALF_UP);
    return Cp.multiply(value.sqrt(new MathContext(10)));
  }
  
  private BigDecimal calcQuantity(BigDecimal area, BigDecimal velocity) {
    return BigDecimal.valueOf(3600).multiply(area).multiply(velocity);
  }
  
  private BigDecimal findPitotTubeCoefficient(
      List<PitotTubeSnapshotDoc.PitotCoefficient> list,
      BigDecimal v
  ) {
    BigDecimal result = BigDecimal.valueOf(0.84);
    
    for (var pc : list) {
      if (v.compareTo(pc.getVelocity()) >= 0) {
        result = pc.getCoefficient();
      }
    }
    
    return result;
  }
}
