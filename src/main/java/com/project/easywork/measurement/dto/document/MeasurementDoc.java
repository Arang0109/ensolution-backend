package com.project.easywork.measurement.dto.document;

import com.project.easywork.measurement.dto.MeasurementStatus;
import com.project.easywork.measurement.dto.document.input.*;
import com.project.easywork.measurement.dto.document.result.MeasurementResultDoc;
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
public class MeasurementDoc {
  
  @Id
  private String id;
  
  @Indexed
  private Long planId;
  private MeasurementStatus status;
  
  private Integer measurementPointCnt;
  
  private PreInfoDoc preInfo;
  private EquipmentDoc equipment;
  private ClientDoc client;
  private WeatherDoc weather;
  private MoistureDoc moisture;
  private ExhaustGasDoc exhaustGas;
  
  private List<MeasurementPointDoc> measurementPoints;
  
  private MeasurementResultDoc result;
  
  @CreatedDate
  private LocalDateTime createdAt;
  
  @LastModifiedDate
  private LocalDateTime updatedAt;
  
  public void replaceMeasurementItems(List<PreInfoDoc.StackMeasurementDoc> items) {
    if (this.preInfo == null) {
      throw new IllegalStateException("PreInfoDocument가 먼저 생성되어야 합니다.");
    }
    this.preInfo.replaceMeasurementItems(items);
  }
  
  public void changeMeasurementPointCnt(int cnt) {
    this.measurementPointCnt = cnt;
  }
  
  public void updateClient(ClientDoc client) {
    if (this.client == null) {
      this.client = client;
    } else {
      this.client.merge(client);
    }
  }
  
  public void updateWeather(WeatherDoc weather) {
    if (this.weather == null) {
      this.weather = weather;
    } else {
      this.weather.merge(weather);
    }
  }
  
  public void updateMoisture(MoistureDoc moisture) {
    if (this.moisture == null) {
      this.moisture = moisture;
    } else {
      this.moisture.merge(moisture);
    }
  }
  
  public void updateExhaustGas(ExhaustGasDoc exhaustGas) {
    if (this.exhaustGas == null) {
      this.exhaustGas = exhaustGas;
    } else {
      this.exhaustGas.merge(exhaustGas);
    }
  }
  
  public void complete(MeasurementResultDoc result) {
    this.result = result;
    this.status = MeasurementStatus.COMPLETED;
  }
  
  public boolean isDraft() {
    return this.status == MeasurementStatus.DRAFT;
  }
}