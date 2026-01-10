package com.project.easywork.measurement.dto.document;

import com.project.easywork.measurement.dto.MeasurementStatus;
import com.project.easywork.measurement.dto.document.input.*;
import com.project.easywork.measurement.dto.document.result.MeasurementResultDocument;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("measurements")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDocument {
  
  @Id
  private String id;
  
  @Indexed
  private Long planId;
  private MeasurementStatus status;
  
  private Integer measurementPointCnt;
  
  private PreInfoDocument preInfo;
  private ClientDocument client;
  private WeatherDocument weather;
  private MoistureDocument moisture;
  private ExhaustGasDocument exhaustGas;
  
  private List<MeasurementPointDocument> measurementPoints;
  
  private MeasurementResultDocument result;
  
  @CreatedDate
  private LocalDateTime createdAt;
  
  @LastModifiedDate
  private LocalDateTime updatedAt;
  
  public void addMeasurementItems(List<PreInfoDocument.StackMeasurementDocument> items) {
    if (this.preInfo == null) {
      throw new IllegalStateException("PreInfoDocument가 먼저 생성되어야 합니다.");
    }
    this.preInfo.addMeasurementItems(items);
  }
  
  public void replaceMeasurementItems(List<PreInfoDocument.StackMeasurementDocument> items) {
    if (this.preInfo == null) {
      throw new IllegalStateException("PreInfoDocument가 먼저 생성되어야 합니다.");
    }
    this.preInfo.replaceMeasurementItems(items);
  }
  
  public void updatePreInfo(PreInfoDocument preInfo) {
    if (this.preInfo == null) {
      this.preInfo = preInfo;
    } else {
      this.preInfo.merge(preInfo);
    }
  }
  
  public void updateWeather(WeatherDocument weather) {
    if (this.weather == null) {
      this.weather = weather;
    } else {
      this.weather.merge(weather);
    }
  }
  
  public void updateMoisture(MoistureDocument moisture) {
    if (this.moisture == null) {
      this.moisture = moisture;
    } else {
      this.moisture.merge(moisture);
    }
  }
  
  public void updateExhaustGas(ExhaustGasDocument exhaustGas) {
    if (this.exhaustGas == null) {
      this.exhaustGas = exhaustGas;
    } else {
      this.exhaustGas.merge(exhaustGas);
    }
  }
  
  public void complete(MeasurementResultDocument result) {
    this.result = result;
    this.status = MeasurementStatus.COMPLETED;
  }
  
  public boolean isDraft() {
    return this.status == MeasurementStatus.DRAFT;
  }
}