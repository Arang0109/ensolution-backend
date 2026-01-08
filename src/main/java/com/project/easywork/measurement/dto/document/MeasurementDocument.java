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
  private String vehicleNumber;
  
  private PreInfoDocument preInfo;
  private WeatherDocument weather;
  private MoistureDocument moisture;
  private ExhaustGasDocument exhaustGas;
  
  private MeasurementResultDocument result;
  
  @CreatedDate
  private LocalDateTime createdAt;
  
  @LastModifiedDate
  private LocalDateTime updatedAt;
  
  public void updatePreInfo(PreInfoDocument preInfo) {
    this.preInfo = preInfo;
  }
  
  public void updateWeather(WeatherDocument weather) {
    this.weather = weather;
  }
  
  public void updateMoisture(MoistureDocument moisture) {
    this.moisture = moisture;
  }
  
  public void updateExhaustGas(ExhaustGasDocument exhaustGas) {
    this.exhaustGas = exhaustGas;
  }
  
  public void complete(MeasurementResultDocument result) {
    this.result = result;
    this.status = MeasurementStatus.COMPLETED;
  }
  
  public boolean isDraft() {
    return this.status == MeasurementStatus.DRAFT;
  }
}