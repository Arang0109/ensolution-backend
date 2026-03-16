package com.project.easywork.measurement.dto.document.input;

import com.project.easywork.client.domain.Cycle;
import com.project.easywork.pollutant.domain.Method;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalTime;

@Getter
@Builder(toBuilder = true)
@ToString
public class MeasurementItemDoc {
  private Long stackMeasurementId;
  private Long pollutantId;
  private String pollutantNameKr;
  private String pollutantNameEn;
  private Method method;
  private String testEquipment;
  private String testMethod;
  private Double samplingTime;
  private String samplingVolume;
  private Cycle cycle;
  private BigDecimal allowance;
  
  
  private LocalTime startTime;
  private LocalTime endTime;
  
  public MeasurementItemDoc merge(MeasurementItemDoc patch) {
    return this.toBuilder()
        .pollutantNameKr(patch.getPollutantNameKr() != null ? patch.getPollutantNameKr() : this.pollutantNameKr)
        .pollutantNameEn(patch.getPollutantNameEn() != null ? patch.getPollutantNameEn() : this.pollutantNameEn)
        .method(patch.getMethod() != null ? patch.getMethod() : this.method)
        .testMethod(patch.getTestMethod() != null ? patch.getTestMethod() : this.testMethod)
        .samplingTime(patch.getSamplingTime() != null ? patch.getSamplingTime() : this.samplingTime)
        .samplingVolume(patch.getSamplingVolume() != null ? patch.getSamplingVolume() : this.samplingVolume)
        .startTime(patch.getStartTime() != null ? patch.getStartTime() : this.startTime)
        .endTime(patch.getEndTime() != null ? patch.getEndTime() : this.endTime)
        .build();
  }
}