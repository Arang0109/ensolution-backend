package com.project.easywork.measurement.service.impl.draft;

import com.project.easywork.client.domain.persistance.StackMeasurement;
import com.project.easywork.client.service_data.impl.StackMeasurementDataService;
import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.equipment.service.impl.EquipmentService;
import com.project.easywork.measurement.dto.SaveDraftCommandD;
import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.measurement.dto.document.input.MeasurementEquipmentDoc;
import com.project.easywork.measurement.dto.document.input.MeasurementItemDoc;
import com.project.easywork.measurement.mapper.EquipmentDocMapper;
import com.project.easywork.measurement.mapper.snapshot_mapper.MeasurementEquipmentSnapshotMapper;
import com.project.easywork.measurement.util.MeasurementPointCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DraftPatchFactory {
  
  private final MeasurementPointCalculator measurementPointCalculator;
  private final MeasurementEquipmentSnapshotMapper measurementEquipmentSnapshotMapper;
  private final EquipmentService equipmentService;
  private final StackMeasurementDataService stackMeasurementDataService;
  private final EquipmentDocMapper equipmentDocMapper;
  
  public MeasurementDoc buildDraftPatch(
      MeasurementDoc doc,
      SaveDraftCommandD command
  ) {
    // 장비 변경 유무 체크
    MeasurementEquipmentDoc equipmentPatch = buildEquipmentsPatch(command);
    // 측정항목 변경 유무 체크
    List<MeasurementItemDoc> stackMeasurementPatch = buildMeasurementItemsPatch(
        command
    );
    
    Integer measurementPointCnt = doc.getMeasurementPointCnt();
    
    // 측정점 재계산
    if (command.client() != null) {
      measurementPointCnt = measurementPointCalculator.calculate(
          command.client().getStack().getShape(),
          command.client().getStack().getHorizontalLength(),
          command.client().getStack().getVerticalLength()
      );
    }
    
    return doc.patch(command, stackMeasurementPatch, equipmentPatch, measurementPointCnt);
  }
  
  private List<MeasurementItemDoc> buildMeasurementItemsPatch(
      SaveDraftCommandD commandD
      ) {
    
    return commandD.measurementItems().stream()
      .map(command -> {
      return MeasurementItemDoc.builder()
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
        .startTime(command.startTime())
        .endTime(command.endTime())
        .build();
    })
    .toList();
  }
  
  private MeasurementEquipmentDoc buildEquipmentsPatch(SaveDraftCommandD request) {
    EquipmentDoc particleSampler = getIfPresent(request.particleSamplerId());
    EquipmentDoc gasSampler = getIfPresent(request.gasSamplerId());
    EquipmentDoc pitotTube = getIfPresent(request.pitotTubeId());
    EquipmentDoc nozzle = getIfPresent(request.nozzleId());
    
    return equipmentDocMapper.toDoc(
      measurementEquipmentSnapshotMapper.toSnapshot(
        particleSampler, gasSampler, pitotTube, nozzle
      )
    );
  }
  
  private EquipmentDoc getIfPresent(String id) {
    return id == null ? null : equipmentService.getEquipment(id);
  }
}