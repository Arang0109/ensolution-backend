package com.project.easywork.measurement.service.impl;

import com.project.easywork.equipment.domain.persistance.Equipment;
import com.project.easywork.equipment.domain.persistance.PitotTube;
import com.project.easywork.equipment.service_data.IEquipmentDataService;
import com.project.easywork.equipment.service_data.IPitotTubeDataService;
import com.project.easywork.measurement.dto.document.input.EquipmentDoc;
import com.project.easywork.measurement.dto.document.result.MeasurementResultDoc;
import com.project.easywork.measurement.dto.DraftUpdateCommandD;
import com.project.easywork.measurement.dto.command.MeasurementCommandD;
import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.measurement.service.IMeasurementQueryService;
import com.project.easywork.measurement.service.IMeasurementService;
import com.project.easywork.measurement.service_data.IMeasurementDataService;
import com.project.easywork.measurement.util.MeasurementPointCalculator;
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
  
  private final MeasurementDocumentFactory documentFactory;
  private final MeasurementDraftUpdater draftUpdater;
  private final MeasurementResultProcessor resultProcessor;
  private final MeasurementPointCalculator measurementPointCalculator;
  
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
    draftUpdater.updateDraft(document, request);
    
    document.changeMeasurementPointCnt(
        measurementPointCalculator.calculate(
            request.client().stack().shape(),
            request.client().stack().horizontalLength(),
            request.client().stack().verticalLength()
        )
    );
    
    measurementDataService.save(document);
  }
  
  @Override
  public void deleteDraft(Long planId) {
    measurementDataService.deleteByPlanId(planId);
  }
  
  @Override
  public void saveDocument(Long planId, MeasurementCommandD dto) {
    
    MeasurementDoc doc = measurementDataService.findByPlanId(planId);
    
    MeasurementResultDoc result =
        resultProcessor.process(dto);
    
    doc.complete(result);
    
    measurementDataService.save(doc);
  }
  
  @Override
  public void changeEquipment(Long planId, Long equipmentId) {
    Equipment particularEquipment = equipmentDataService.findById(equipmentId);
    MeasurementDoc doc = measurementDataService.findByPlanId(planId);
    
    doc.getEquipment().changeParticularEquipment(
        EquipmentDoc.ParticularEquipmentDoc.builder()
            .particularEquipmentId(particularEquipment.getId())
            .modelName(particularEquipment.getModelName())
            .equipmentName(particularEquipment.getEquipmentName())
            .deltaH(particularEquipment.getDh())
            .Yd(particularEquipment.getYd())
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
            .pitotTubeId(pitotTube.getId())
            .modelName(pitotTube.getModelName())
            .equipmentName(pitotTube.getEquipmentName())
            .coefficients(coefficientDocs)
            .build()
    );
    
    measurementDataService.save(doc);
  }
}
