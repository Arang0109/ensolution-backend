package com.project.easywork.plan.service;

import com.project.easywork.measurement.domain.dto.command.SaveDraftCommandD;
import com.project.easywork.measurement.domain.dto.command.StatusUpdateCommandD;
import com.project.easywork.plan.domain.dto.PlanCreateBundleD;
import com.project.easywork.plan.domain.dto.PlanD;
import com.project.easywork.plan.domain.dto.PlanDetailD;
import com.project.easywork.plan.domain.dto.PlanTableViewD;

import java.util.List;

public interface IPlanService {
  List<PlanTableViewD> getList();
  PlanDetailD getPlan(Long planId);
  PlanD registerOnlyPlan(PlanCreateBundleD dto);
  PlanD updateStatus(Long planId, StatusUpdateCommandD dto);
  void updatePlanFromPreInfo(Long planId, SaveDraftCommandD request);
  void delete(Long planId);
}
