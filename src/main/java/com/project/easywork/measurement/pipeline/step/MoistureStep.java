package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.common.domain.PressureUnit;
import com.project.easywork.common.util.GasCalculator;
import com.project.easywork.common.util.PressureConverter;
import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.measurement.dto.document.input.MoistureDoc;
import com.project.easywork.measurement.pipeline.domain.Measurement;
import com.project.easywork.measurement.pipeline.context.MeasurementContext;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 수분량 계산
 * | ( ( 22.4 / 18 ) * diffWeight ) /                                                     | * 100
 * | ( diffVolume * ( 273 / 273 + T ) * ( P / 760 ) ) + ( ( 22.4 / 18 ) * diffWeight )    |
 */
public class MoistureStep implements MeasurementStep {
  
  @Override
  public void execute(MeasurementContext context) {
    
    Measurement domain = context.getDomain();
    
    MeasurementDoc measurement = domain.getMeasurement();
    MoistureDoc moisture  = measurement.getMoisture();
    
    // 흡수병 무게 차이
    BigDecimal diffWeight =
        moisture.getWeight().getAfter()
            .subtract(moisture.getWeight().getBefore());;
    
    // 부피 차이
    BigDecimal diffVolume =
        moisture.getDryGasVolume().getAfter()
            .subtract(moisture.getDryGasVolume().getBefore());
    
    // 평균 온도
    BigDecimal avgTemp =
        moisture.getGasMeterTemperature().getIn()
            .add(moisture.getGasMeterTemperature().getOut())
            .divide(BigDecimal.valueOf(2), 6, RoundingMode.HALF_UP);
    
    // 압력 = 대기압 + 게이지압
    BigDecimal pressure =
        measurement.getWeather().getConvertedPressure()
            .add(PressureConverter.toMmHg(
                moisture.getGasMeterGaugePressure(),
                PressureUnit.MMH2O));
    
    // 표준 가스 부피
    BigDecimal standardGasVolume =
        GasCalculator.toStandardDensity(diffVolume, avgTemp, pressure);
    
    // 무게 → 부피 변환
    BigDecimal diffWeightToVolume =
        diffWeight
            .multiply(BigDecimal.valueOf(22.4))
            .divide(BigDecimal.valueOf(18), 10, RoundingMode.HALF_UP);
    
    // 분모
    BigDecimal denominator =
        standardGasVolume.add(diffWeightToVolume);
    
    // 수분 함량(%)
    BigDecimal moistureContent =
        diffWeightToVolume
            .divide(denominator, 6, RoundingMode.HALF_UP)
            .multiply(BigDecimal.valueOf(100));
    
    MoistureDoc updatedMoistureRatio = moisture.toBuilder()
        .moistureRatio(moistureContent)
        .build();
    
    MeasurementDoc updatedMeasurement = measurement.toBuilder()
        .moisture(updatedMoistureRatio)
        .build();
    
    domain.updateMeasurement(updatedMeasurement);
  }
}