package com.project.easywork.measurement.dto.document.input;

import com.project.easywork.plan.domain.MeasurementCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementSheetDoc {
  private MeasurementCategory category; // 측정 카테고리 (먼지, 중금속, 수은, PM10, PM2.5, 기타)
  private String referenceNumber; // 문서번호 (부)
  
  private WeatherDoc weather; // 날씨정보
  private MoistureDoc moisture; // 수분정보
  private ExhaustGasDoc exhaustGas; // 배출가스정보
  
  private List<MeasurementPointDoc> measurementPoints; // 측정점데이터
  
  private BigDecimal quantity; // 유량
  private BigDecimal pitotTubeCoefficient; // 피토우관 계수
  
  private LocalTime startTime; // 측정 시작시간
  private LocalTime endTime; // 측정 종료시간
}
