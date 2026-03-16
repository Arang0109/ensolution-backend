package com.project.easywork.plan.service.impl;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.common.resolver.DomainEntityResolver;
import com.project.easywork.measurement.dto.SaveDraftCommandD;
import com.project.easywork.measurement.dto.StatusUpdateCommandD;
import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.measurement.mapper.MeasurementDocMapper;
import com.project.easywork.measurement.service_data.IMeasurementDataService;
import com.project.easywork.plan.domain.dto.*;
import com.project.easywork.plan.domain.persistance.Plan;
import com.project.easywork.plan.mapper.PlanMapper;
import com.project.easywork.plan.service.IPlanService;
import com.project.easywork.plan.service_data.IPlanDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanService implements IPlanService {
  
  private final IPlanDataService planDataService;
  private final IMeasurementDataService measurementDataService;
  
  private final PlanMapper planMapper;
  private final MeasurementDocMapper measurementDocMapper;
  
  private final DomainEntityResolver domainEntityResolver;
  
  @Override
  public PlanD registerOnlyPlan(PlanCreateBundleD dto) {
    PlanCreateD planD = dto.getPlan();
    
    Stack stack = domainEntityResolver.getStackOrThrow(planD.getStackId());
    Team team = domainEntityResolver.getTeamOrThrow(planD.getTeamId());
    
    Plan plan = planMapper.toEntity(planD);
    plan.attachStack(stack);
    plan.attachTeam(team);
    plan.createPlan();
    
    Plan savedPlan = planDataService.save(plan);
    
    return planMapper.toDto(savedPlan);
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<PlanTableViewD> getList() {
    List<MeasurementDoc> docs = measurementDataService.findAll();
    return measurementDocMapper.toTableList(docs);
  }
  
  @Override
  @Transactional(readOnly = true)
  public PlanDetailD getPlan(Long planId) {
    Plan plan = planDataService.findDetailById(planId);
    MeasurementDoc doc =
        measurementDataService.findByPlanId(planId);
    
    return PlanDetailD.builder()
        .plan(planMapper.toDto(plan))
        .measurementInfo(doc)
        .build();
  }
  
  @Override
  public PlanD updateStatus(Long planId, StatusUpdateCommandD dto) {
    Plan plan = planDataService.findById(planId);
    plan.updateStatus(dto);
    return planMapper.toDto(plan);
  }
  
  @Override
  public void updatePlanFromPreInfo(Long planId, SaveDraftCommandD request) {
    Plan plan = planDataService.findById(planId);
    plan.updatePlan(request);
  }
  
  @Override
  public void delete(Long planId) {
    planDataService.deleteById(planId);
  }
}
