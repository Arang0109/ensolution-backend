package com.project.easywork.measurement.service.impl;

import com.project.easywork.measurement.dto.document.result.MeasurementResultDoc;
import com.project.easywork.measurement.dto.DraftUpdateCommandD;
import com.project.easywork.measurement.dto.command.MeasurementCommandD;
import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.measurement.service.IMeasurementQueryService;
import com.project.easywork.measurement.service.IMeasurementService;
import com.project.easywork.measurement.service_data.IMeasurementDataService;
import com.project.easywork.plan.domain.dto.PlanCreateBundleD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MeasurementService implements IMeasurementService {
  
  private final IMeasurementDataService measurementDataService;
  private final IMeasurementQueryService measurementQueryService;
  
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
    draftUpdater.updateDraft(document, request);
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
}
