package com.project.easywork.measurement.pipeline.context;

import com.project.easywork.measurement.pipeline.domain.Measurement;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MeasurementContext {
  
  private final Measurement domain;
  
  private BigDecimal avgTs;
  private BigDecimal avgVs;
  private BigDecimal avgPd;
  private BigDecimal avgPs;
  private BigDecimal avgGasDensity;
  
  public MeasurementContext(Measurement domain) {
    this.domain = domain;
  }
}