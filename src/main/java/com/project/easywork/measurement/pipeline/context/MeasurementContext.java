package com.project.easywork.measurement.pipeline.context;

import com.project.easywork.measurement.domain.Measurement;
import com.project.easywork.measurement.dto.document.result.MeasurementResultDocument;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeasurementContext {
  
  private final Measurement domain;
  
  // 1. PressureConvertStep
  private Double weatherPressureMmHg;
  private Double staticPressureMmHg;
  private Double gasEquipGaugePressureMmHg;
  
  // 2. MoistureCalculateStep
  private Double moistureContent;
  
  // 3. GasDensityCalculateStep
  private Double gasDensity;
  
  // 4.
  private Double oxygenCorrected;
  
  private MeasurementResultDocument result;
  
  // 추가로 필요한 값들 계속 확장 가능
  
  public MeasurementContext(Measurement domain) {
    this.domain = domain;
  }
}

