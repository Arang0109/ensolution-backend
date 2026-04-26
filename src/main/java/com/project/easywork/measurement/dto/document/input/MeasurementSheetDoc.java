package com.project.easywork.measurement.dto.document.input;

import com.project.easywork.plan.domain.MeasurementCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementSheetDoc {
  private MeasurementCategory category; // 측정 카테고리 (먼지, 중금속, 수은, PM10, PM2.5, 기타)
  
  private WeatherDoc weather; // 날씨정보
  private MoistureDoc moisture; // 수분정보
  private ExhaustGasDoc exhaustGas; // 배출가스정보
  
  private List<MeasurementPointDoc> measurementPoints; // 측정점데이터
  private List<SampleDoc> samples;
  
  // 입자상 물질 채취시 필요한 필드
  private ParticleSampleDoc particleSample;
  
  // 최종 계산 필드
  
  private BigDecimal avgTg; // 배출가스 절대온도
  private BigDecimal avgPv; // 배출가스 동압
  private BigDecimal avgPs; // 배출가스 정압
  private BigDecimal avgTm; // 가스미터 절대온도
  
  private BigDecimal quantity; // 유량
}
