package com.project.easywork.measurement.service.impl;

import com.project.easywork.measurement.dto.SaveDraftCommandD;
import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.measurement.service.IMeasurementQueryService;
import com.project.easywork.measurement.service.IMeasurementService;
import com.project.easywork.measurement.service.impl.draft.DraftCreateFactory;
import com.project.easywork.measurement.service.impl.draft.DraftPatchFactory;
import com.project.easywork.measurement.service.impl.processor.MeasurementResultProcessor;
import com.project.easywork.measurement.service_data.IMeasurementDataService;
import com.project.easywork.plan.domain.dto.PlanCreateBundleD;
import com.project.easywork.measurement.dto.StatusUpdateCommandD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MeasurementService implements IMeasurementService {
  
  private final IMeasurementDataService measurementDataService;
  private final IMeasurementQueryService measurementQueryService;
  
  private final DraftCreateFactory draftCreateFactory;
  private final DraftPatchFactory draftPatchFactory;
  private final MeasurementResultProcessor resultProcessor;
  
  @Override
  public void submitDocument(Long planId) {
    
    MeasurementDoc doc = measurementDataService.findByPlanId(planId);
    
    MeasurementDoc result = resultProcessor.process(doc);
    
    measurementDataService.save(doc.complete(result));
  }
  
  @Override
  public void saveDraft(Long planId, SaveDraftCommandD command) {
    MeasurementDoc document = measurementDataService.findByPlanId(planId);
    MeasurementDoc updated = draftPatchFactory.buildDraftPatch(document, command);
    measurementDataService.save(updated);
  }
  
  @Override
  public void createDraft(Long planId, PlanCreateBundleD command) {
    
    MeasurementDoc doc = draftCreateFactory.createDraft(
        planId,
        measurementQueryService.createSnapshot(command)
    );
    measurementDataService.save(doc);
  }
  
  @Override
  public void updateStatus(Long planId, StatusUpdateCommandD command) {
    MeasurementDoc document = measurementDataService.findByPlanId(planId);
    MeasurementDoc updated = document.updateStatus(command);
    measurementDataService.save(updated);
  }
  
  @Override
  public void deleteDraft(Long planId) {
    measurementDataService.deleteByPlanId(planId);
  }
}
