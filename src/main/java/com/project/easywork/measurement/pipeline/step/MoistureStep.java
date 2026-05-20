package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.measurement.domain.document.sheets.MoistureDoc;
import com.project.easywork.measurement.pipeline.SheetContext;
import com.project.easywork.measurement.pipeline.domain.Sheet;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@Order(3)
@RequiredArgsConstructor
public class MoistureStep implements SheetStep {
  
  @Override
  public void execute(SheetContext context) {
    Sheet sheet = context.getSheet();
    MoistureDoc moisture = sheet.getSheet().getMoisture();
    
    // ma: 흡습된 수분의 질량 (g)
    BigDecimal ma = moisture.getWeight().getAfter().subtract(moisture.getWeight().getBefore());
    
    // Tm_g: 가스미터에서의 흡입 가스온도 (°C)
    BigDecimal Tm_g = moisture.getGasMeterTemperature().getIn()
        .add(moisture.getGasMeterTemperature().getOut())
        .divide(BigDecimal.TWO, 1, RoundingMode.HALF_UP);
    // Vm_g: 흡입한 건조가스량 (L)
    BigDecimal Vm_g = moisture.getDryGasVolume().getAfter().subtract(moisture.getDryGasVolume().getBefore());
    
    BigDecimal Pm = context.getPa().add(context.getPm_g());
    BigDecimal waterVolStp = calcWaterVolumeStp(ma);
    BigDecimal dryVolStp = convertToSTP(Vm_g, Tm_g, Pm);
    // Xw: 배출가스 중의 수증기의 부피 백분율 (%)
    BigDecimal Xw = calcMoistureRatioPure(waterVolStp, dryVolStp);
    
    context.setXw(Xw);
    context.setTm_g(Tm_g);
    context.setVm_g(Vm_g);
    context.setMa(ma);
  }
  
  private BigDecimal convertToSTP(BigDecimal value, BigDecimal temperature, BigDecimal pressure) {
    BigDecimal t = BigDecimal.valueOf(273);
    BigDecimal p = BigDecimal.valueOf(760);
    
    return value
        .multiply(t.divide(t.add(temperature), 5, RoundingMode.HALF_UP))
        .multiply(pressure.divide(p, 5, RoundingMode.HALF_UP));
  }
  
  private BigDecimal calcWaterVolumeStp(BigDecimal waterG) {
    return waterG.multiply(
      BigDecimal.valueOf(22.4).divide(BigDecimal.valueOf(18), 5, RoundingMode.HALF_UP)
    );
  }
  
  private BigDecimal calcMoistureRatioPure(BigDecimal waterVolStp, BigDecimal dryVolStp) {
    return BigDecimal.valueOf(100).multiply(
        waterVolStp.divide(waterVolStp.add(dryVolStp), 5, RoundingMode.HALF_UP)
    );
  }
}
