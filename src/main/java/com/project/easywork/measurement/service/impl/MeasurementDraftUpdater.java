package com.project.easywork.measurement.service.impl;

import com.project.easywork.client.domain.persistance.StackMeasurement;
import com.project.easywork.client.service_data.impl.StackMeasurementDataService;
import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.equipment.service.impl.EquipmentService;
import com.project.easywork.measurement.dto.DraftUpdateCommandD;
import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.measurement.dto.document.input.MeasurementEquipmentDoc;
import com.project.easywork.measurement.dto.document.input.PreInfoDoc;
import com.project.easywork.measurement.mapper.EquipmentDocMapper;
import com.project.easywork.measurement.mapper.snapshot_mapper.MeasurementEquipmentSnapshotMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MeasurementDraftUpdater {
  
  private final MeasurementEquipmentSnapshotMapper measurementEquipmentSnapshotMapper;
  private final EquipmentService equipmentService;
  private final StackMeasurementDataService stackMeasurementDataService;
  private final EquipmentDocMapper equipmentDocMapper;
  
  public MeasurementDoc updateDraft(
      MeasurementDoc doc,
      DraftUpdateCommandD request
  ) {
    // 장비 변경 유무 체크
    MeasurementEquipmentDoc equipmentPatch = updateEquipments(request);
    // 측정항목 변경 유무 체크
    List<PreInfoDoc.StackMeasurementDoc> stackMeasurementPatch = updateStackMeasurements(request);
    
    return doc.apply(request, stackMeasurementPatch, equipmentPatch);
  }
  
  private List<PreInfoDoc.StackMeasurementDoc> updateStackMeasurements(DraftUpdateCommandD request) {
    List<Long> pollutantIdList = request.pollutantIdList();
    return pollutantIdList.stream().map(
        id -> {
          StackMeasurement sm = stackMeasurementDataService.findById(id);
          return PreInfoDoc.StackMeasurementDoc.builder()
            .stackMeasurementId(sm.getId())
            .pollutantId(sm.getPollutant().getId())
            .pollutantNameKr(sm.getPollutant().getNameKr())
            .pollutantNameEn(sm.getPollutant().getNameEn())
            .method(sm.getPollutant().getMethod())
            .equipmentName(sm.getPollutant().getEquipmentName())
            .testMethodName(sm.getPollutant().getTestMethodName())
            .cycle(sm.getCycle())
            .allowance(sm.getAllowance())
            .build();
        }).toList();
  }
  
  private MeasurementEquipmentDoc updateEquipments(DraftUpdateCommandD request) {
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