package com.project.easywork.plan.service.impl;

import com.project.easywork.measurement.dto.SaveDraftCommandD;
import com.project.easywork.measurement.service.IMeasurementService;
import com.project.easywork.plan.domain.dto.PlanCreateBundleD;
import com.project.easywork.plan.domain.dto.PlanD;
import com.project.easywork.measurement.dto.StatusUpdateCommandD;
import com.project.easywork.plan.service.IPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanApplicationService {
  
  private final IPlanService planService;
  private final IMeasurementService measurementService;
  
  public PlanD register(PlanCreateBundleD command) {
    PlanD plan = planService.registerOnlyPlan(command);
    measurementService.createDraft(plan.getId(), command);
    
    return plan;
  }
  
  public void saveDraft(Long planId, SaveDraftCommandD command) {
    planService.updatePlanFromPreInfo(planId, command);
    measurementService.saveDraft(planId, command);
  }
  
  public PlanD updateStatus(Long planId, StatusUpdateCommandD command) {
    PlanD plan = planService.updateStatus(planId, command);
    measurementService.updateStatus(planId, command);
    return plan;
  }
  
  public void delete(Long planId) {
    planService.delete(planId);
    measurementService.deleteDraft(planId);
  }
}