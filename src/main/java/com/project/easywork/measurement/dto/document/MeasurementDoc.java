package com.project.easywork.measurement.dto.document;

import com.project.easywork.measurement.dto.SaveDraftCommandD;
import com.project.easywork.measurement.dto.document.input.*;
import com.project.easywork.plan.domain.MeasurementField;
import com.project.easywork.plan.domain.PlanStatus;
import com.project.easywork.measurement.dto.StatusUpdateCommandD;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
  private Long planId; // 측정계획 ID
  private Long teamId; // 측정팀 ID
  
  private PlanStatus status; // 측정상태 (MEASURING, COMPLETED)
  private String referenceNumber; // 문서번호 (주)
  private LocalDate measureDate; // 측정날짜
  private LocalDate receivedDate; // 접수날짜
  private LocalDate analysisDate; // 분석날짜
  private MeasurementField measurementField; // 측정분야
  private String measurementType; // 측정용도
  private String teamName; // 측정팀
  private String vehicleNumber; // 측정차량
  private String mentor; // 사수
  private String mentee; // 부사수
  
  private ClientDoc client; // 의뢰기관 정보
  private MeasurementEquipmentDoc equipment; // 측정장비 정보
  private List<MeasurementItemDoc> measurementItems; // 측정항목
  
  private List<MeasurementSheetDoc> sheets; // 측정 데이터
  
  private Integer measurementPointCnt; // 측정점 수
  
  @CreatedDate
  private LocalDateTime createdAt;
  
  @LastModifiedDate
  private LocalDateTime updatedAt;
  
  public MeasurementDoc updateStatus(StatusUpdateCommandD dto) {
    return this.toBuilder()
        .status(dto.status())
        .build();
  }
  
  public boolean isCompleted() {
    return this.status == PlanStatus.COMPLETED;
  }
  
  public MeasurementDoc complete(MeasurementDoc doc) {
    return this.toBuilder()
        .build();
  }
  
  public MeasurementDoc patch(
      SaveDraftCommandD command,
      List<MeasurementItemDoc> stackMeasurementPatch,
      MeasurementEquipmentDoc equipmentPatch,
      Integer measurementPointCnt
  ) {
    if (isCompleted()) {
      throw new IllegalStateException("작성완료된 보고서는 수정 불가능");
    }
    
    return this.toBuilder()
        .referenceNumber(command.referenceNumber() == null ? this.referenceNumber : command.referenceNumber())
        .measureDate(command.measureDate() == null ? this.measureDate : command.measureDate())
        .receivedDate(command.receivedDate() == null ? this.receivedDate : command.receivedDate())
        .analysisDate(command.analysisDate() == null ? this.analysisDate : command.analysisDate())
        .measurementField(command.measurementField() == null ? this.measurementField : command.measurementField())
        .measurementType(command.measurementType() == null ? this.measurementType : command.measurementType())
        .teamName(command.teamName() == null ? this.teamName : command.teamName())
        .vehicleNumber(command.vehicleNumber() == null ? this.vehicleNumber : command.vehicleNumber())
        .mentor(command.mentor() == null ? this.mentor : command.mentor())
        .mentee(command.mentee() == null ? this.mentee : command.mentee())
        .client(command.client() == null ? this.client : mergeClient(command.client()))
        .equipment(mergeEquipment(equipmentPatch))
        .measurementItems(mergeMeasurementItems(stackMeasurementPatch))
        .sheets(command.sheets())
        .measurementPointCnt(measurementPointCnt)
        .build();
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
  
  private List<MeasurementItemDoc> mergeMeasurementItems(List<MeasurementItemDoc> patch) {
    if (patch == null) return this.measurementItems;
    
    return new ArrayList<>(patch);
  }
}