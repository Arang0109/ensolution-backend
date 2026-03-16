package com.project.easywork.plan.service;

import com.project.easywork.measurement.dto.SaveDraftCommandD;
import com.project.easywork.measurement.dto.StatusUpdateCommandD;
import com.project.easywork.plan.domain.dto.*;

import java.util.List;

public interface IPlanService {
  List<PlanTableViewD> getList();
  PlanDetailD getPlan(Long planId);
  PlanD registerOnlyPlan(PlanCreateBundleD dto);
  PlanD updateStatus(Long planId, StatusUpdateCommandD dto);
  void updatePlanFromPreInfo(Long planId, SaveDraftCommandD request);
  void delete(Long planId);
}
