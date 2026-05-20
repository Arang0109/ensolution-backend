package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.measurement.domain.document.sheets.MoistureDoc;
import com.project.easywork.measurement.domain.document.sheets.WeatherDoc;
import com.project.easywork.measurement.pipeline.SheetContext;
import com.project.easywork.measurement.pipeline.domain.Sheet;
import com.project.easywork.measurement.util.Calculator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@Order(2)
@RequiredArgsConstructor
public class PressureStep implements SheetStep {
  
  private final Calculator calculator;
  
  @Override
  public void execute(SheetContext context) {
    setAtmospherePressure(context);
    setGasMeterGaugePressure(context);
    setPg(context);
  }
  
  // 대기압 변환
  private void setAtmospherePressure(SheetContext context) {
    Sheet sheet = context.getSheet();
    WeatherDoc weather = sheet.getSheet().getWeather();
    
    BigDecimal Pa_hpa = weather.getPressure().getPressure();
    context.setPa_hpa(Pa_hpa);
    
    if (Pa_hpa == null) return;
    context.setPa(convertHpaToMmHg(Pa_hpa));
  }
  
  // 수분 가스미터 게이지압 변환
  private void setGasMeterGaugePressure(SheetContext context) {
    Sheet sheet = context.getSheet();
    MoistureDoc moisture = sheet.getSheet().getMoisture();
    
    BigDecimal gasMeterGaugePressure = moisture.getGasMeterGaugePressure();
    context.setGasMeterPg(gasMeterGaugePressure);
    
    if (gasMeterGaugePressure == null) return;
    context.setPm_g(convertMmH2OToMmHg(gasMeterGaugePressure));
    context.setPm_g_inchH2O(convertMmH2OToInchH2O(gasMeterGaugePressure));
  }
  
  // 측정점 동압, 정압
  private void setPg(SheetContext context) {
    BigDecimal Pg = context.getPa().add(convertMmH2OToMmHg(context.getAvgPs()));
    context.setPg(Pg);
  }
  
  // Hpa >> mmHg 변환
  private BigDecimal convertHpaToMmHg(BigDecimal value) {
    return value
        .multiply(BigDecimal.valueOf(760.0))
        .divide(BigDecimal.valueOf(1013.25), 1, RoundingMode.HALF_UP);
  }
  
  // mmH2O >> inchH2O 변환
  private BigDecimal convertMmH2OToInchH2O(BigDecimal value) {
    return value.divide(BigDecimal.valueOf(25.4), 1, RoundingMode.HALF_UP);
  }
  
  // mmH2O >> mmHg 변환
  private BigDecimal convertMmH2OToMmHg(BigDecimal value) {
    return value.divide(BigDecimal.valueOf(13.6), 2, RoundingMode.HALF_UP);
  }
}
