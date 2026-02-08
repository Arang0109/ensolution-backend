package com.project.easywork.measurement.service.impl;

import com.project.easywork.measurement.dto.DraftUpdateCommandD;
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
}
