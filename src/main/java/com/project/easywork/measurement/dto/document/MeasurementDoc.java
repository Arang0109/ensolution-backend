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
  
  public MeasurementDoc updatePreInfo(PreInfoDoc patch) {
    if (this.preInfo == null) {
      return this.toBuilder().preInfo(patch).build();
    }
    
    return this.toBuilder()
        .preInfo(this.preInfo.merge(patch))
        .build();
  }
  
  public MeasurementDoc updateClient(ClientDoc patch) {
    if (this.client == null) {
      return this.toBuilder().client(patch).build();
    }
    
    return this.toBuilder()
        .client(this.client.merge(patch))
        .build();
  }
  
  public MeasurementDoc updateEquipment(MeasurementEquipmentDoc patch) {
    if (!isDraft()) {
      throw new IllegalStateException("Draft 상태에서만 수정 가능");
    }
    
    MeasurementEquipmentDoc merged =
        this.equipment == null
            ? patch
            : this.equipment.merge(patch);
    
    return this.toBuilder()
        .equipment(merged)
        .build();
  }
  
  public boolean isDraft() {
    return this.status == MeasurementStatus.DRAFT;
  }
}