package com.project.easywork.plan.service.impl;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.client.domain.dto.stack.MeasurementHistoryD;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.common.resolver.DomainEntityResolver;
import com.project.easywork.measurement.dto.document.MeasurementDocument;
import com.project.easywork.measurement.service.IMeasurementService;
import com.project.easywork.measurement.service_data.IMeasurementDataService;
import com.project.easywork.plan.domain.dto.*;
import com.project.easywork.plan.domain.persistance.Plan;
import com.project.easywork.plan.mapper.PlanMapper;
import com.project.easywork.plan.mapper.PlanMeasurementMapper;
import com.project.easywork.plan.service.IPlanMeasurementService;
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
  
  private final IPlanMeasurementService scheduleMeasurementService;
  private final PlanMeasurementMapper planMeasurementMapper;
  private final IMeasurementService measurementService;
  
  private final DomainEntityResolver domainEntityResolver;
  
  @Override
  public PlanD register(PlanCreateBundleD dto) {
    PlanCreateD planD = dto.getPlan();
    
    Stack stack = domainEntityResolver.getStackOrThrow(planD.getStackId());
    Team team = domainEntityResolver.getTeamOrThrow(planD.getTeamId());
    
    Plan plan = planMapper.toEntity(planD);
    plan.attachStack(stack);
    plan.attachTeam(team);
    
    Plan savedPlan = planDataService.save(plan);
    Long planId = savedPlan.getId(); ;
    
    scheduleMeasurementService.registerAll(
        planId,  planD.getMeasurementIds());
    
    measurementService.createDraft(planId, dto);
    
    return planMapper.toDto(planDataService.save(savedPlan));
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<PlanTableViewD> getList() {
    return planMapper.toTableList(planDataService.findAllWithTeamAndStack());
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<MeasurementHistoryD> getListByStack(Long stackId) {
    return planMapper.toMeasurementHistory(
        planDataService.findCompletedByStackId(stackId)
    );
  }
  
  @Override
  @Transactional(readOnly = true)
  public PlanDetailD getPlan(Long planId) {
    Plan plan = planDataService.findDetailById(planId);
    MeasurementDocument doc =
        measurementDataService.findByPlanId(planId);
    
    return PlanDetailD.builder()
        .plan(planMapper.toDto(plan))
        .status(doc.getStatus())
        .preInfo(doc.getPreInfo())
        .weather(doc.getWeather())
        .moisture(doc.getMoisture())
        .exhaustGas(doc.getExhaustGas())
        .result(doc.getResult())
        .build();
  }
  
  @Override
  public void addMeasurements(Long planId, List<MeasurementItemsCreateD> dtos) {
    Plan plan = planDataService.findById(planId);
    
    plan.updateMeasurements(
        planMeasurementMapper.toEntityList(dtos)
    );
    
    planDataService.save(plan);
  }
  
  @Override
  public PlanD updateStatus(Long planId, StatusUpdateD dto) {
    Plan plan = planDataService.findById(planId);
    plan.updateStatus(dto);
    return planMapper.toDto(plan);
  }
  
  @Override
  public void delete(Long planId) {
    planDataService.deleteById(planId);
    measurementService.deleteDraft(planId);
  }
}
