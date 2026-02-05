package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.common.domain.PressureUnit;
import com.project.easywork.common.util.PressureConverter;
import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.measurement.dto.document.input.MeasurementEquipmentDoc;
import com.project.easywork.measurement.dto.document.input.MeasurementPointDoc;
import com.project.easywork.measurement.pipeline.context.MeasurementContext;
import com.project.easywork.measurement.pipeline.domain.Measurement;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class QuantityStep implements MeasurementStep {
  
  private static final BigDecimal STD_TEMPERATURE = new BigDecimal("273.0");
  private static final BigDecimal STD_PRESSURE = new BigDecimal("760.0");
  
  // 1차 계산용 기본 피토우관 계수
  private static final BigDecimal PITOT_DEFAULT = new BigDecimal("8.40");
  
  @Override
  public void execute(MeasurementContext context) {
    Measurement domain = context.getDomain();
    MeasurementDoc measurement = domain.getMeasurement();
    
    BigDecimal atomPressure = convertAtmosphericPressure(measurement);
    BigDecimal stdGasDensity = measurement.getExhaustGas().getGasDensity();
    List<MeasurementPointDoc> points = measurement.getMeasurementPoints();
    
    // 1. 기본 계수로 1차 계산
    CalculationResult first =
        calculate(points, atomPressure, stdGasDensity, PITOT_DEFAULT);
    
    // 2. 평균 유속으로 실제 피토우관 계수 선택
    BigDecimal selectedCoefficient = findCoefficient(
        first.avgVs,
        measurement.getEquipment().getPitotTube().getCoefficients()
    );
    
    // 3. 선택된 계수로 2차 계산 (최종값)
    CalculationResult finalResult =
        calculate(points, atomPressure, stdGasDensity, selectedCoefficient);
    
    // 4. MeasurementDoc 갱신
    domain.updateMeasurement(
        measurement.toBuilder()
            .measurementPoints(finalResult.updatedPoints)
            .pitotTubeCoefficient(selectedCoefficient)
            .quantity(finalResult.avgVs)
            .build()
    );
    
    // 5. Context에 최종 평균 저장
    context.setAvgTs(finalResult.avgTs);
    context.setAvgVs(finalResult.avgVs);
    context.setAvgPd(finalResult.avgPd);
    context.setAvgPs(finalResult.avgPs);
    context.setAvgGasDensity(finalResult.avgGasDensity);
  }

  /* ==============================
        Core Calculation
     ============================== */
  
  private CalculationResult calculate(
      List<MeasurementPointDoc> points,
      BigDecimal atomPressure,
      BigDecimal stdGasDensity,
      BigDecimal pitotCoeff
  ) {
    Accumulator acc = new Accumulator();
    
    List<MeasurementPointDoc> updatedPoints = points.stream()
        .map(p -> calculatePoint(p, atomPressure, stdGasDensity, pitotCoeff, acc))
        .toList();
    
    int count = points.size();
    
    return new CalculationResult(
        updatedPoints,
        acc.avg(acc.sumTs, count),
        acc.avg(acc.sumVs, count),
        acc.avg(acc.sumPd, count),
        acc.avg(acc.sumPs, count),
        acc.avg(acc.sumGasDensity, count)
    );
  }
  
  private MeasurementPointDoc calculatePoint(
      MeasurementPointDoc doc,
      BigDecimal atomPressure,
      BigDecimal stdGasDensity,
      BigDecimal pitotCoeff,
      Accumulator acc
  ) {
    BigDecimal Ts = doc.getGasTemperature();
    BigDecimal Pd = doc.getDynamicPressure();
    BigDecimal Ps = PressureConverter.toMmHg(doc.getStaticPressure(), PressureUnit.MMH2O);
    
    // 온도 보정
    BigDecimal tempFactor =
        STD_TEMPERATURE.divide(STD_TEMPERATURE.add(Ts), 10, RoundingMode.HALF_UP);
    
    // 압력 보정
    BigDecimal pressureFactor =
        atomPressure.add(Ps).divide(STD_PRESSURE, 10, RoundingMode.HALF_UP);
    
    // 가스 밀도 계산
    BigDecimal gasDensity =
        stdGasDensity.multiply(tempFactor).multiply(pressureFactor);
    
    // 속도 계산
    BigDecimal inside =
        Pd.multiply(new BigDecimal("19.62"))
            .divide(gasDensity, 10, RoundingMode.HALF_UP);
    
    BigDecimal velocity =
        pitotCoeff.multiply(sqrt(inside, 10))
            .setScale(2, RoundingMode.HALF_UP);
    
    acc.add(Ts, velocity, Pd, Ps, gasDensity);
    
    return doc.toBuilder()
        .gasDensity(gasDensity)
        .gasVelocity(velocity)
        .build();
  }

  /* ==============================
        Pitot Coefficient
     ============================== */
  
  private BigDecimal findCoefficient(
      BigDecimal avgVs,
      List<MeasurementEquipmentDoc.PitotTubeSnapshot.PitotCoefficient> coeffs
  ) {
    for (MeasurementEquipmentDoc.PitotTubeSnapshot.PitotCoefficient c : coeffs) {
      // avgVs < 기준 속도 → 해당 구간 계수
      if (avgVs.compareTo(c.getVelocity()) < 0) {
        return c.getCoefficient();
      }
    }
    // 가장 큰 구간보다 크면 마지막 계수 사용
    return coeffs.get(coeffs.size() - 1).getCoefficient();
  }

  /* ==============================
        Utils
     ============================== */
  
  private BigDecimal convertAtmosphericPressure(MeasurementDoc measurement) {
    return PressureConverter.toMmHg(
        measurement.getWeather().getPressure().getPressure(),
        PressureUnit.from(measurement.getWeather().getPressure().getUnit())
    );
  }
  
  public static BigDecimal sqrt(BigDecimal value, int scale) {
    BigDecimal two = BigDecimal.valueOf(2);
    BigDecimal x0 = BigDecimal.ZERO;
    BigDecimal x1 = new BigDecimal(Math.sqrt(value.doubleValue()));
    
    while (!x0.equals(x1)) {
      x0 = x1;
      x1 = value.divide(x0, scale, RoundingMode.HALF_UP);
      x1 = x1.add(x0);
      x1 = x1.divide(two, scale, RoundingMode.HALF_UP);
    }
    return x1;
  }

  /* ==============================
        Inner Classes
     ============================== */
  
  @AllArgsConstructor
  private static class CalculationResult {
    List<MeasurementPointDoc> updatedPoints;
    BigDecimal avgTs;
    BigDecimal avgVs;
    BigDecimal avgPd;
    BigDecimal avgPs;
    BigDecimal avgGasDensity;
  }
  
  private static class Accumulator {
    BigDecimal sumTs = BigDecimal.ZERO;
    BigDecimal sumVs = BigDecimal.ZERO;
    BigDecimal sumPd = BigDecimal.ZERO;
    BigDecimal sumPs = BigDecimal.ZERO;
    BigDecimal sumGasDensity = BigDecimal.ZERO;
    
    void add(BigDecimal Ts, BigDecimal Vs, BigDecimal Pd, BigDecimal Ps, BigDecimal density) {
      sumTs = sumTs.add(Ts);
      sumVs = sumVs.add(Vs);
      sumPd = sumPd.add(Pd);
      sumPs = sumPs.add(Ps);
      sumGasDensity = sumGasDensity.add(density);
    }
    
    BigDecimal avg(BigDecimal sum, int count) {
      return sum.divide(BigDecimal.valueOf(count), 5, RoundingMode.HALF_UP);
    }
  }
}