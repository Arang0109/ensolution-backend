package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.measurement.dto.document.input.ExhaustGasDoc;
import com.project.easywork.measurement.pipeline.SheetContext;
import com.project.easywork.measurement.pipeline.domain.Sheet;
import com.project.easywork.measurement.util.Calculator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
@Order(4)
@RequiredArgsConstructor
public class ExhaustGasStep implements SheetStep {
  
  private final Calculator calc;
  private final BigDecimal O2_MOL = BigDecimal.valueOf(0.32);
  private final BigDecimal CO2_MOL = BigDecimal.valueOf(0.44);
  private final BigDecimal CO_MOL = BigDecimal.valueOf(0.28);
  private final BigDecimal N2_MOL = BigDecimal.valueOf(0.28);
  
  @Override
  public void execute(SheetContext context) {
    Sheet sheet = context.getSheet();
    ExhaustGasDoc exhaustGas = sheet.getSheet().getExhaustGas();
    
    List<BigDecimal> o2List = exhaustGas.getO2Concentration();
    List<BigDecimal> co2List = exhaustGas.getCo2Concentration();
    List<BigDecimal> coList = exhaustGas.getCoConcentration();
    
    BigDecimal o2 = calc.averageTreatNullAsZero(o2List, 1);
    BigDecimal co2 = calc.averageTreatNullAsZero(co2List, 1);
    BigDecimal co = calc.averageTreatNullAsZero(coList, 1);
    BigDecimal n2 = calcNitrogenAvg(o2, co2, co);
    
    BigDecimal Xw = context.getXw();
    
    BigDecimal standardOxygen = context.getStandardOxygen();
    if (standardOxygen != null) {
      BigDecimal oxygenCorrectionFactor = calcOxygenCorrectionFactor(standardOxygen, o2);
      context.setOxygenCorrectionFactor(oxygenCorrectionFactor);
    }
    
    BigDecimal Md = calcDryMolecularWeight(o2, co2, co, n2);
    BigDecimal Mw = calcMolecularWeight(Md, Xw);
    
    context.setO2(o2);
    context.setCo2(co2);
    context.setCo(co);
    context.setN2(n2);
    context.setMd(Md);
    context.setMw(Mw);
  }
  
  private BigDecimal calcDryMolecularWeight(BigDecimal o2, BigDecimal co2, BigDecimal co, BigDecimal n2) {
    return O2_MOL.multiply(o2).add(CO2_MOL.multiply(co2)).add(CO_MOL.multiply(co)).add(N2_MOL.multiply(n2));
  }
  
  private BigDecimal calcMolecularWeight(BigDecimal Md, BigDecimal Xw) {
    BigDecimal B = Xw.divide(BigDecimal.valueOf(100), 5, RoundingMode.HALF_UP);
    
    return Md.multiply(BigDecimal.ONE.subtract(B)).add(BigDecimal.valueOf(18).multiply(B));
  }
  
  private BigDecimal calcNitrogenAvg(BigDecimal o2, BigDecimal co2, BigDecimal co) {
    return BigDecimal.valueOf(100).subtract(o2.add(co2).add(co));
  }
  
  private BigDecimal calcOxygenCorrectionFactor(BigDecimal standardOxygen, BigDecimal o2) {
    return BigDecimal.valueOf(21).subtract(standardOxygen)
        .divide(BigDecimal.valueOf(21).subtract(o2), 5, RoundingMode.HALF_UP);
  }
}
