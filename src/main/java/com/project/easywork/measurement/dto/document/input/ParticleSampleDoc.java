package com.project.easywork.measurement.dto.document.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalTime;

@Getter
@Builder(toBuilder = true)
public class ParticleSampleDoc {
  @JsonProperty("Cp")
  private BigDecimal Cp; // 피토우관 계수
  private BigDecimal nozzleSize; // 노즐 사이즈 (cm)
  
  @JsonProperty("Vm")
  private BigDecimal Vm; // 건식가스미터에서 읽은 채취량
  private BigDecimal samplingTime; // 채취시간
  
  private BigDecimal kFactor; // K 계수 (평균)
  private BigDecimal orificeDp; // 오리피스 차압 (평균)
  private BigDecimal isokineticRatio; // 등속흡입계수 (평균)
  
  private LocalTime samplingStartTime; // 측정 시작시간
  private LocalTime samplingEndTime; // 측정 종료시간
}