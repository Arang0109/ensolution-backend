package com.project.easywork.measurement.dto.document;

import com.project.easywork.measurement.dto.MeasurementStatus;
import com.project.easywork.measurement.dto.document.input.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document("measurements")
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDoc {
  
  @Id
  private String id;
  
  @Indexed
  private Long planId;
  private MeasurementStatus status;
  
  private Integer measurementPointCnt;
  private List<BigDecimal> circularAxisCoords;
  
  private PreInfoDoc preInfo;
  private MeasurementEquipmentDoc equipment;
  private ClientDoc client;
  private WeatherDoc weather;
  private MoistureDoc moisture;
  private ExhaustGasDoc exhaustGas;
  
  private List<MeasurementPointDoc> measurementPoints;
  
  private BigDecimal pitotTubeCoefficient;
  private BigDecimal quantity;
  
  @CreatedDate
  private LocalDateTime createdAt;
  
  @LastModifiedDate
  private LocalDateTime updatedAt;
  
  public MeasurementDoc complete(MeasurementDoc doc) {
    return this.toBuilder()
        .weather(doc.weather)
        .moisture(doc.moisture)
        .exhaustGas(doc.exhaustGas)
        .status(MeasurementStatus.COMPLETED)
        .build();
  }
  
  public void replaceMeasurementItems(List<PreInfoDoc.StackMeasurementDoc> items) {
    if (this.preInfo == null) {
      throw new IllegalStateException("PreInfoDocument가 먼저 생성되어야 합니다.");
    }
    this.preInfo.replaceMeasurementItems(items);
  }
  
  public void changePitotTubeCoefficient(BigDecimal coefficient) {
    this.pitotTubeCoefficient = coefficient;
  }
  
  public void changeMeasurementPointCnt(int cnt) {
    this.measurementPointCnt = cnt;
  }
  
  public MeasurementDoc updateStackInfo(ClientDoc.StackDoc stackInfo) {
    if (this.client == null) return this;
    
    ClientDoc newClient = this.client.toBuilder()
        .stack(stackInfo)
        .build();
    
    return this.toBuilder()
        .client(newClient)
        .build();
  }
  
  public MeasurementDoc updateWeather(WeatherDoc weather) {
    return this.toBuilder()
        .weather(weather)
        .build();
  }
  
  public MeasurementDoc updateMoisture(MoistureDoc moisture) {
    return this.toBuilder()
        .moisture(moisture)
        .build();
  }
  
  public MeasurementDoc updateExhaustGas(ExhaustGasDoc exhaustGas) {
    return this.toBuilder()
        .exhaustGas(exhaustGas)
        .build();
  }
  
  public boolean isDraft() {
    return this.status == MeasurementStatus.DRAFT;
  }
}