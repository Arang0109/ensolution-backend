package com.project.easywork.measurement.domain.document.sheets;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalTime;

@Getter
@Builder(toBuilder = true)
public class SampleDoc {
  private String sampleName;
  private LocalTime startTime;
  private LocalTime endTime;
  private BigDecimal suctionQuantity;
  private BigDecimal gasMeterGaugePressure;
  private BigDecimal inTemperature;
  private BigDecimal outTemperature;
  private BigDecimal beforeVolume;
  private BigDecimal afterVolume;
  private String blankSampleNumber;
  private String sampleNumber;
  private BigDecimal samplingVolume;
}