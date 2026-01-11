package com.project.easywork.plan.service.impl;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.client.domain.dto.stack.MeasurementHistoryD;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.client.domain.persistance.StackMeasurement;
import com.project.easywork.client.service_data.IStackMeasurementDataService;
import com.project.easywork.common.resolver.DomainEntityResolver;
import com.project.easywork.equipment.domain.persistance.Equipment;
import com.project.easywork.equipment.domain.persistance.PitotTube;
import com.project.easywork.equipment.domain.persistance.PitotTubeCoefficient;
import com.project.easywork.equipment.service_data.IEquipmentDataService;
import com.project.easywork.equipment.service_data.IPitotTubeDataService;
import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.measurement.dto.document.input.EquipmentDoc;
import com.project.easywork.measurement.dto.document.input.PreInfoDoc;
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
  private final IStackMeasurementDataService stackMeasurementDataService;
  private final PlanMapper planMapper;
  
  private final IPlanMeasurementService scheduleMeasurementService;
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
    MeasurementDoc doc =
        measurementDataService.findByPlanId(planId);
    
    return PlanDetailD.builder()
        .plan(planMapper.toDto(plan))
        .status(doc.getStatus())
        .measurementPointCnt(doc.getMeasurementPointCnt())
        .preInfo(doc.getPreInfo())
        .equipment(doc.getEquipment())
        .client(doc.getClient())
        .weather(doc.getWeather())
        .moisture(doc.getMoisture())
        .exhaustGas(doc.getExhaustGas())
        .result(doc.getResult())
        .build();
  }
  
  @Override
  public void replaceMeasurements(Long planId, List<MeasurementItemsUpdateD> dtos) {
    Plan plan = planDataService.findById(planId);
    
    plan.replaceMeasurements(
        dtos,
        stackMeasurementDataService::findById
    );
    
    MeasurementDoc doc = measurementDataService.findByPlanId(planId);
    
    List<PreInfoDoc.StackMeasurementDoc> stackDocs =
        dtos.stream()
            .map(dto -> {
              StackMeasurement sm = stackMeasurementDataService
                  .findById(dto.getStackMeasurementId());
              
              return PreInfoDoc.StackMeasurementDoc.builder()
                  .stackMeasurementId(sm.getId())
                  .pollutantId(sm.getPollutant().getId())
                  .pollutantNameKr(sm.getPollutant().getNameKr())
                  .pollutantNameEn(sm.getPollutant().getNameEn())
                  .cycle(sm.getCycle())
                  .allowance(sm.getAllowance())
                  .build();
            })
            .toList();
    
    doc.replaceMeasurementItems(stackDocs);
    measurementDataService.save(doc);
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
