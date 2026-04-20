package com.project.easywork.measurement.pipeline;

import com.project.easywork.common.pipeline.Context;
import com.project.easywork.measurement.dto.document.input.ClientDoc;
import com.project.easywork.measurement.dto.document.input.MeasurementEquipmentDoc;
import com.project.easywork.measurement.pipeline.domain.Sheet;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class SheetContext implements Context {
  
  private final ClientDoc client;
  private final MeasurementEquipmentDoc equipment;
  private final Sheet sheet;
  
  // Part. 사용자 입력값
  private BigDecimal Pa_hpa; // 대기압 (Hpa)
  private BigDecimal GasMeterPg; // 가스미터 게이지압 (mmH2O)
  
  // Part. 계산 값
  private BigDecimal Pa; // 대기압 (mmHg)
  private BigDecimal Pg; // 배출가스 절대압력 (mmHg)
  private BigDecimal Pm_g;  // 수분측정을 위한 가스미터 게이지압 (mmHg)
  private BigDecimal Pm_g_inchH2O;  // 수분측정을 위한 가스미터 게이지압 (inchH2O)
  
  private BigDecimal Xw; // 수분량 (%)
  private BigDecimal Tm_g; // 가스미터에서의 흡입 가스온도 (°C)
  private BigDecimal Vm_g; // 흡입한 건조가스량 (L)
  private BigDecimal ma; // 흡습된 수분의 질량 (g)
  
  private BigDecimal standardOxygen;
  private BigDecimal o2;
  private BigDecimal co2;
  private BigDecimal co;
  private BigDecimal n2;
  
  private BigDecimal oxygenCorrectionFactor; // 산소보정계수
  private BigDecimal Md; // 건조배출가스 분자량
  private BigDecimal Mw; // 습윤배출가스 분자량
  
  private BigDecimal standardGasDensity;
  private BigDecimal gasDensity;
  
  private BigDecimal Cp;
  
  private BigDecimal avgTg; // 배출가스 평균 온도 (K)
  private BigDecimal avgPv; // 배출가스 평균 동압 (mmH2O)
  private BigDecimal avgPs; // 배출가스 평균 정압 (mmH2O)
  private BigDecimal avgVs; // 배출가스 평균 유속 (m/s)
  private BigDecimal avgTm; // 가스미터 평균 온도 (K)
  
  public SheetContext(ClientDoc client, MeasurementEquipmentDoc equipment, Sheet sheet) {
    this.client = client;
    this.equipment = equipment;
    this.sheet = sheet;
  }
}