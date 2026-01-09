package com.project.easywork.measurement.service.impl;

import com.project.easywork.measurement.dto.document.result.MeasurementResultDocument;
import com.project.easywork.measurement.dto.MeasurementDraftUpdateCommandDto;
import com.project.easywork.measurement.dto.command.MeasurementCommandDto;
import com.project.easywork.measurement.dto.document.MeasurementDocument;
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
  
  private final MeasurementDocumentFactory documentFactory;
  private final MeasurementDraftUpdater draftUpdater;
  private final MeasurementResultProcessor resultProcessor;
  
  @Override
  public void createDraft(Long planId, PlanCreateBundleD dto) {
    MeasurementDocument doc = documentFactory.createDraft(planId, dto);
    measurementDataService.save(doc);
  }
  
  @Override
  public void updateDraft(Long planId, MeasurementDraftUpdateCommandDto request) {
    MeasurementDocument document = measurementDataService.findByPlanId(planId);
    draftUpdater.updateDraft(document, request);
    measurementDataService.save(document);
  }
  
  @Override
  public void deleteDraft(Long planId) {
    measurementDataService.deleteByPlanId(planId);
  }
  
  @Override
  public void saveDocument(Long planId, MeasurementCommandDto dto) {
    
    MeasurementDocument doc = measurementDataService.findByPlanId(planId);
    
    MeasurementResultDocument result =
        resultProcessor.process(dto);
    
    doc.complete(result);
    
    measurementDataService.save(doc);
  }
}
