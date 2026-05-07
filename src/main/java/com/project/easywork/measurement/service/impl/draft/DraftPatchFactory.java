package com.project.easywork.measurement.service.impl.draft;

import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.equipment.service.impl.EquipmentService;
import com.project.easywork.measurement.domain.dto.command.SaveDraftCommandD;
import com.project.easywork.measurement.domain.document.MeasurementDoc;
import com.project.easywork.measurement.domain.document.equipments.EquipmentSnapshotDoc;
import com.project.easywork.measurement.domain.document.items.MeasurementItemSnapshotDoc;
import com.project.easywork.measurement.domain.dto.patch.DraftPatchD;
import com.project.easywork.measurement.mapper.BasicInfoDocMapper;
import com.project.easywork.measurement.mapper.EquipmentDocMapper;
import com.project.easywork.measurement.mapper.TeamDocMapper;
import com.project.easywork.measurement.mapper.draft_source_mapper.EquipmentSourceMapper;
import com.project.easywork.measurement.util.MeasurementPointCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DraftPatchFactory {
  
  private final MeasurementPointCalculator measurementPointCalculator;
  private final EquipmentSourceMapper equipmentSourceMapper;
  private final EquipmentService equipmentService;
  
  private final BasicInfoDocMapper basicInfoDocMapper;
  private final TeamDocMapper teamDocMapper;
  private final EquipmentDocMapper equipmentDocMapper;
  
  public DraftPatchD buildDraftPatch(
      MeasurementDoc doc,
      SaveDraftCommandD command
  ) {
    // 장비 변경 유무 체크
    EquipmentSnapshotDoc equipmentPatch = buildEquipmentsPatch(command);
    // 측정항목 변경 유무 체크
    List<MeasurementItemSnapshotDoc> measurementItemsPatch = buildMeasurementItemsPatch(command);
    
    Integer measurementPointCnt = doc.getBasicInfo().getMeasurementPointCnt();
    
    // 측정점 재계산
    if (command.client() != null) {
      measurementPointCnt = measurementPointCalculator.calculate(
          command.client().getStack().getShape(),
          command.client().getStack().getHorizontalLength(),
          command.client().getStack().getVerticalLength()
      );
    }
    
    return DraftPatchD.builder()
        .basicInfo(basicInfoDocMapper.toPatch(command, measurementPointCnt))
        .team(teamDocMapper.toPatch(command))
        .client(command.client())
        .equipment(equipmentPatch)
        .measurementItems(measurementItemsPatch)
        .sheets(command.sheets())
        .build();
  }
  
  private List<MeasurementItemSnapshotDoc> buildMeasurementItemsPatch(
      SaveDraftCommandD commandD
      ) {
    
    return commandD.measurementItems().stream()
      .map(command -> {
      return MeasurementItemSnapshotDoc.builder()
        .stackMeasurementId(command.stackMeasurementId())
        .pollutantId(command.pollutantId())
        .pollutantNameKr(command.pollutantNameKr())
        .pollutantNameEn(command.pollutantNameEn())
        .method(command.method())
        .testEquipment(command.testEquipment())
        .testMethod(command.testMethod())
        .samplingTime(command.samplingTime())
        .samplingVolume(command.samplingVolume())
        .cycle(command.cycle())
        .allowance(command.allowance())
        .build();
    })
    .toList();
  }
  
  private EquipmentSnapshotDoc buildEquipmentsPatch(SaveDraftCommandD request) {
    EquipmentDoc particleSampler = getIfPresent(request.particleSamplerId());
    EquipmentDoc gasSampler = getIfPresent(request.gasSamplerId());
    EquipmentDoc pitotTube = getIfPresent(request.pitotTubeId());
    EquipmentDoc nozzle = getIfPresent(request.nozzleId());
    
    return equipmentDocMapper.toDoc(
      equipmentSourceMapper.toSnapshot(
        particleSampler, gasSampler, pitotTube, nozzle
      )
    );
  }
  
  private EquipmentDoc getIfPresent(String id) {
    return id == null ? null : equipmentService.getEquipment(id);
  }
}