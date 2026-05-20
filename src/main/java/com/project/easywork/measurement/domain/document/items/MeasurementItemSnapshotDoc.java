package com.project.easywork.measurement.domain.document.items;

import com.project.easywork.client.domain.Cycle;
import com.project.easywork.pollutant.domain.Method;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Builder(toBuilder = true)
@ToString
public class MeasurementItemSnapshotDoc {
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
  
  public MeasurementItemSnapshotDoc merge(MeasurementItemSnapshotDoc patch) {
    return this.toBuilder()
        .pollutantNameKr(patch.getPollutantNameKr() != null ? patch.getPollutantNameKr() : this.pollutantNameKr)
        .pollutantNameEn(patch.getPollutantNameEn() != null ? patch.getPollutantNameEn() : this.pollutantNameEn)
        .method(patch.getMethod() != null ? patch.getMethod() : this.method)
        .testEquipment(patch.getTestEquipment() != null ? patch.getTestEquipment() : this.testEquipment)
        .testMethod(patch.getTestMethod() != null ? patch.getTestMethod() : this.testMethod)
        .samplingTime(patch.getSamplingTime() != null ? patch.getSamplingTime() : this.samplingTime)
        .samplingVolume(patch.getSamplingVolume() != null ? patch.getSamplingVolume() : this.samplingVolume)
        .cycle(patch.getCycle() != null ? patch.getCycle() : this.cycle)
        .allowance(patch.getAllowance() != null ? patch.getAllowance() : this.allowance)
        .build();
  }
}