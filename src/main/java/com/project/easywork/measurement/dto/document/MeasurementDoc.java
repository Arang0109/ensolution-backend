package com.project.easywork.measurement.dto.document;

import com.project.easywork.measurement.dto.DraftUpdateCommandD;
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
  
  public MeasurementDoc apply(
      DraftUpdateCommandD cmd,
      List<PreInfoDoc.StackMeasurementDoc> stackMeasurementPatch,
      MeasurementEquipmentDoc equipmentPatch
  ) {
    if (!isDraft()) {
      throw new IllegalStateException("Draft 상태만 수정 가능");
    }
    
    return this.toBuilder()
        .preInfo(mergePreInfo(cmd.preInfo(), stackMeasurementPatch))
        .client(mergeClient(cmd.client()))
        .equipment(mergeEquipment(equipmentPatch))
        .build();
  }
  
  private PreInfoDoc mergePreInfo(
      PreInfoDoc patch,
      List<PreInfoDoc.StackMeasurementDoc> measurementPatch) {
    PreInfoDoc base = this.preInfo;
    
    if (base == null) { base = patch != null ? patch : PreInfoDoc.builder().build(); }
    
    if (patch != null) { base = base.merge(patch, null); }
    
    if (measurementPatch != null) {
      base = base.toBuilder()
          .measurementItems(measurementPatch)
          .build();
    }
    
    return base;
  }
  
  private ClientDoc mergeClient(ClientDoc patch) {
    if (patch == null) return this.client;
    if (this.client == null) return patch;
    
    return this.client.merge(patch);
  }
  
  private MeasurementEquipmentDoc mergeEquipment(MeasurementEquipmentDoc patch) {
    if (patch == null) return this.equipment;
    if (this.equipment == null) return patch;
    
    return this.equipment.merge(patch);
  }
  
  public boolean isDraft() {
    return this.status == MeasurementStatus.DRAFT;
  }
}