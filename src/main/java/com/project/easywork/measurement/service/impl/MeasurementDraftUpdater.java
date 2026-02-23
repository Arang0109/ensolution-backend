package com.project.easywork.measurement.service.impl;

import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.equipment.service.impl.EquipmentService;
import com.project.easywork.measurement.dto.DraftUpdateCommandD;
import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.measurement.mapper.EquipmentDocMapper;
import com.project.easywork.measurement.mapper.snapshot_mapper.MeasurementEquipmentSnapshotMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeasurementDraftUpdater {
  
  private final MeasurementEquipmentSnapshotMapper measurementEquipmentSnapshotMapper;
  private final EquipmentService equipmentService;
  private final EquipmentDocMapper equipmentDocMapper;
  
  public MeasurementDoc updateDraft(
      MeasurementDoc doc,
      DraftUpdateCommandD request
  ) {
    if (!doc.isDraft()) { throw new IllegalStateException("Draft 상태만 수정 가능"); }
    MeasurementDoc updated = doc;
    
    if (request.preInfo() != null) { updated = updated.updatePreInfo(request.preInfo()); }
    
    EquipmentDoc particleSampler = getIfPresent(request.particleSamplerId());
    EquipmentDoc gasSampler = getIfPresent(request.gasSamplerId());
    EquipmentDoc pitotTube = getIfPresent(request.pitotTubeId());
    EquipmentDoc nozzle = getIfPresent(request.nozzleId());
    
    updated = updated.updateEquipment(
        equipmentDocMapper.toDoc(
            measurementEquipmentSnapshotMapper.toSnapshot(
                particleSampler, gasSampler, pitotTube, nozzle
            )
        )
    );
    
    if (request.client() != null) { updated = updated.updateClient(request.client()); }
    
    return updated;
  }
  
  private EquipmentDoc getIfPresent(String id) {
    return id == null ? null : equipmentService.getEquipment(id);
  }
}