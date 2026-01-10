package com.project.easywork.plan.service;

import com.project.easywork.client.domain.dto.stack.MeasurementHistoryD;
import com.project.easywork.plan.domain.dto.*;

import java.util.List;

public interface IPlanService {
  List<PlanTableViewD> getList();
  List<MeasurementHistoryD> getListByStack(Long stackId);
  PlanDetailD getPlan(Long planId);
  PlanD register(PlanCreateBundleD dto);
  void replaceMeasurements(Long planId, List<MeasurementItemsUpdateD> dto);
  PlanD updateStatus(Long planId, StatusUpdateD dto);
  void delete(Long planId);
}
