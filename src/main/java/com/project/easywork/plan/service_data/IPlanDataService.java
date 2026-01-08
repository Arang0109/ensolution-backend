package com.project.easywork.plan.service_data;

import com.project.easywork.plan.domain.persistance.Plan;

import java.util.List;

public interface IPlanDataService {
  Plan findById(Long planId);
  Plan findDetailById(Long planId);
  Plan save(Plan plan);
  void deleteById(Long planId);
  List<Plan> findAllWithTeamAndStack();
  List<Plan> findCompletedByStackId(Long stackId);
}