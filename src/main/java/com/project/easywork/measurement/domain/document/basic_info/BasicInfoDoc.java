package com.project.easywork.measurement.domain.document.basic_info;

import com.project.easywork.plan.domain.MeasurementField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BasicInfoDoc {
  private String referenceNumber; // 문서번호 (주)
  private LocalDate measureDate; // 측정날짜
  private LocalDate receivedDate; // 접수날짜
  private LocalDate analysisDate; // 분석날짜
  private MeasurementField measurementField; // 측정분야
  private String measurementType; // 측정용도
  
  private LocalTime measureStartTime;
  private LocalTime measureEndTime;
  private Integer measurementPointCnt; // 측정점 수
  
  public BasicInfoDoc merge(BasicInfoDoc patch) {
    if (patch == null) {
      return this;
    }
    
    return this.toBuilder()
      .referenceNumber(
        patch.getReferenceNumber() != null
          ? patch.getReferenceNumber()
          : this.referenceNumber
      )
      .measureDate(
        patch.getMeasureDate() != null
          ? patch.getMeasureDate()
          : this.measureDate
      )
      .receivedDate(
        patch.getReceivedDate() != null
          ? patch.getReceivedDate()
          : this.receivedDate
      )
      .analysisDate(
        patch.getAnalysisDate() != null
          ? patch.getAnalysisDate()
          : this.analysisDate
      )
      .measurementField(
        patch.getMeasurementField() != null
          ? patch.getMeasurementField()
          : this.measurementField
      )
      .measurementType(
        patch.getMeasurementType() != null
          ? patch.getMeasurementType()
          : this.measurementType
      )
      .measureStartTime(
        patch.getMeasureStartTime() != null
          ? patch.getMeasureStartTime()
          : this.measureStartTime
      )
      .measureEndTime(
        patch.getMeasureEndTime() != null
          ? patch.getMeasureEndTime()
          : this.measureEndTime
      )
      .measurementPointCnt(
        patch.getMeasurementPointCnt() != null
          ? patch.getMeasurementPointCnt()
          : this.measurementPointCnt
      )
      .build();
  }
}