package com.project.easywork.measurement.domain.document;

import com.project.easywork.measurement.domain.document.sheets.MeasurementSheetDoc;
import com.project.easywork.measurement.domain.document.basic_info.BasicInfoDoc;
import com.project.easywork.measurement.domain.document.client.ClientSnapshotDoc;
import com.project.easywork.measurement.domain.document.equipments.EquipmentSnapshotDoc;
import com.project.easywork.measurement.domain.document.items.MeasurementItemSnapshotDoc;
import com.project.easywork.measurement.domain.document.team.TeamSnapshotDoc;
import com.project.easywork.measurement.domain.dto.patch.DraftPatchD;
import com.project.easywork.plan.domain.PlanStatus;
import com.project.easywork.measurement.domain.dto.command.StatusUpdateCommandD;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
  private PlanStatus status; // 측정계획 상태 (MEASURING, COMPLETED)
  
  private BasicInfoDoc basicInfo;
  private TeamSnapshotDoc team;
  private ClientSnapshotDoc client; // 의뢰기관 정보
  private EquipmentSnapshotDoc equipment; // 측정장비 정보
  private List<MeasurementItemSnapshotDoc> measurementItems; // 측정항목 정보
  
  private List<MeasurementSheetDoc> sheets; // 측정 데이터
  
  // 측정계획의 상태 변경
  public MeasurementDoc updateStatus(StatusUpdateCommandD dto) {
    return this.toBuilder()
        .status(dto.status())
        .build();
  }
  
  public MeasurementDoc complete(MeasurementDoc doc) {
    return this.toBuilder()
        .sheets(doc.getSheets())
        .build();
  }
  
  // 임시저장 메서드
  public MeasurementDoc saveDraft(DraftPatchD patch) {
    
    if (isCompleted()) throw new IllegalStateException("작성완료된 보고서는 수정 불가능합니다.");
    
    return this.toBuilder()
        .basicInfo(patch.basicInfo() == null ? this.basicInfo : this.basicInfo.merge(patch.basicInfo()))
        .team(patch.team() == null ? this.team : patch.team())
        .client(patch.client() == null ? this.client : mergeClient(patch.client()))
        .equipment(patch.equipment() == null ? this.equipment : mergeEquipment(patch.equipment()))
        .measurementItems(patch.measurementItems() == null ? this.measurementItems : mergeMeasurementItems(patch.measurementItems()))
        .sheets(patch.sheets() == null ? this.sheets : patch.sheets())
        .build();
  }
  
  private ClientSnapshotDoc mergeClient(ClientSnapshotDoc patch) {
    if (patch == null) return this.client;
    if (this.client == null) return patch;
    
    return this.client.merge(patch);
  }
  
  private EquipmentSnapshotDoc mergeEquipment(EquipmentSnapshotDoc patch) {
    if (patch == null) return this.equipment;
    if (this.equipment == null) return patch;
    
    return this.equipment.merge(patch);
  }
  
  private List<MeasurementItemSnapshotDoc> mergeMeasurementItems(List<MeasurementItemSnapshotDoc> patch) {
    if (patch == null) return this.measurementItems;
    
    return new ArrayList<>(patch);
  }
  
  private boolean isCompleted() {
    return this.status == PlanStatus.COMPLETED;
  }
}