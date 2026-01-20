package com.project.easywork.measurement.service.impl;

import com.project.easywork.equipment.domain.persistance.Equipment;
import com.project.easywork.equipment.domain.persistance.ParticularSampler;
import com.project.easywork.equipment.domain.persistance.PitotTube;
import com.project.easywork.equipment.service_data.IEquipmentDataService;
import com.project.easywork.equipment.service_data.IPitotTubeDataService;
import com.project.easywork.measurement.dto.document.input.EquipmentDoc;
import com.project.easywork.measurement.dto.DraftUpdateCommandD;
import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.measurement.mapper.MeasurementMapper;
import com.project.easywork.measurement.service.IMeasurementQueryService;
import com.project.easywork.measurement.service.IMeasurementService;
import com.project.easywork.measurement.service_data.IMeasurementDataService;
import com.project.easywork.plan.domain.dto.PlanCreateBundleD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MeasurementService implements IMeasurementService {
  
  private final IMeasurementDataService measurementDataService;
  private final IMeasurementQueryService measurementQueryService;
  
  private final IEquipmentDataService equipmentDataService;
  private final IPitotTubeDataService pitotTubeDataService;
  
  private final MeasurementMapper measurementMapper;
  
  private final MeasurementDocumentFactory documentFactory;
  private final MeasurementDraftUpdater draftUpdater;
  private final MeasurementResultProcessor resultProcessor;
  
  @Override
  public void createDraft(Long planId, PlanCreateBundleD dto) {
    
    MeasurementDoc doc = documentFactory.createDraft(
        planId,
        dto.getPlan(),
        measurementQueryService.loadSnapshot(dto)
    );
    measurementDataService.save(doc);
  }
  
  @Override
  public void updateDraft(Long planId, DraftUpdateCommandD request) {
    MeasurementDoc document = measurementDataService.findByPlanId(planId);
    MeasurementDoc updated = draftUpdater.updateDraft(document, request);
    measurementDataService.save(updated);
  }
  
  @Override
  public void deleteDraft(Long planId) {
    measurementDataService.deleteByPlanId(planId);
  }
  
  @Override
  public void saveDocument(Long planId) {
    
    MeasurementDoc doc = measurementDataService.findByPlanId(planId);
    
    MeasurementDoc result = resultProcessor.process(doc);
    
    measurementDataService.save(doc.complete(result));
  }
  
  @Override
  public void changeEquipment(Long planId, Long equipmentId) {
    ParticularSampler particularSampler = equipmentDataService.findById(equipmentId).getParticularSampler();
    MeasurementDoc doc = measurementDataService.findByPlanId(planId);
    
    doc.getEquipment().changeParticularEquipment(
        EquipmentDoc.ParticularEquipmentDoc.builder()
            .equipmentId(particularSampler.getId())
            .alias(particularSampler.getAlias())
            .deltaH(particularSampler.getOrificeDP())
            .Yd(particularSampler.getYd())
            .build()
    );
    
    measurementDataService.save(doc);
  }
  
  @Override
  public void changePitotTube(Long planId, Long pitotTubeId) {
    PitotTube pitotTube = pitotTubeDataService.findById(pitotTubeId);
    MeasurementDoc doc = measurementDataService.findByPlanId(planId);
    
    List<EquipmentDoc.PitotTubeDoc.CoefficientDoc> coefficientDocs =
        pitotTube.getPitotTubeCoefficientList().stream().map(
            c ->
                EquipmentDoc.PitotTubeDoc.CoefficientDoc.builder()
                    .coefficientId(c.getId())
                    .velocity(c.getVelocity())
                    .coefficient(c.getCoefficient())
                    .build()
        ).toList();
    
    doc.getEquipment().changePitotTube(
        EquipmentDoc.PitotTubeDoc.builder()
            .equipmentId(pitotTube.getId())
            .alias(pitotTube.getAlias())
            .type(pitotTube.getType().name())
            .coefficients(coefficientDocs)
            .build()
    );
    
    measurementDataService.save(doc);
  }
}
