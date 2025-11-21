package com.project.easywork.measurement.dto.document.result;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MeasurementResultDocument {
  
  private Double weatherPressureMmHg;
  private Double staticPressureMmHg;
  private Double gasEquipGaugePressureMmHg;
  
  private Double moistureContent;
  private Double gasDensity;
  private Double oxygenCorrected;
  
  private Integer measurementPoint;
  
}
