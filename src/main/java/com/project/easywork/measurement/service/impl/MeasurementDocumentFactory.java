package com.project.easywork.measurement.service.impl;

import com.project.easywork.client.domain.Shape;
import com.project.easywork.client.domain.persistance.*;
import com.project.easywork.common.util.measurePoint.MeasurePointStrategy;
import com.project.easywork.common.util.measurePoint.MeasurePointStrategyFactory;
import com.project.easywork.equipment.domain.persistance.PitotTubeCoefficient;
import com.project.easywork.measurement.dto.MeasurementStatus;
import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.measurement.dto.document.input.ClientDoc;
import com.project.easywork.measurement.dto.document.input.EquipmentDoc;
import com.project.easywork.measurement.dto.document.input.PreInfoDoc;
import com.project.easywork.measurement.dto.snapshot.MeasurementSnapshot;
import com.project.easywork.plan.domain.dto.PlanCreateD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MeasurementDocumentFactory {
  
  public MeasurementDoc createDraft(Long planId, PlanCreateD plan, MeasurementSnapshot s) {
  
    return MeasurementDoc.builder()
        .planId(planId)
        .status(MeasurementStatus.DRAFT)
        .measurementPointCnt(calMeasurementPointCnt(s))
        .preInfo(buildPreInfo(plan, s))
        .equipment(buildEquipment(s))
        .client(buildClient(s))
        .build();
  }
  
  private Integer calMeasurementPointCnt(MeasurementSnapshot s) {
    Shape stackShape = s.stack().getShape();
    BigDecimal horizontal = s.stack().getHorizontalLength();
    BigDecimal vertical   = s.stack().getVerticalLength();
    
    // Draft 단계 기본값
    if (stackShape == null || stackShape == Shape.OTHER) {
      return 1;
    }
    
    MeasurePointStrategy strategy;
    
    switch (stackShape) {
      case RECTANGULAR -> {
        if (horizontal == null || vertical == null) {
          return 1;
        }
        
        strategy = MeasurePointStrategyFactory.of("rectangle");
        return strategy.calculate(horizontal, vertical);
      }
      
      case CIRCULAR -> {
        if (horizontal == null) { // 지름 하나만 있으면 됨
          return 1;
        }
        
        strategy = MeasurePointStrategyFactory.of("circular");
        int point = strategy.calculate(horizontal);
        
        // 간소화 규칙 적용
        return point == 1 ? 1 : point / 4;
      }
      
      default -> {
        return 1;
      }
    }
  }
  
  private PreInfoDoc buildPreInfo(PlanCreateD plan, MeasurementSnapshot s) {
    
    List<String> engineers = new ArrayList<>();
    if (s.senior() != null) {
      engineers.add(s.senior().getName());
    }
    if (s.junior() != null) {
      engineers.add(s.junior().getName());
    }
    
    return PreInfoDoc.builder()
        .measureDate(plan.getMeasureDate())
        .measurementType(plan.getMeasurementType())
        .teamName(safe(s.team().getName()))
        .vehicleNumber(safe(s.vehicleNumber()))
        .engineers(engineers)
        .measurementItems(buildStackMeasurements(s))
        .simplifiedMeasurement(true)
        .build();
  }
  
  private List<PreInfoDoc.StackMeasurementDoc> buildStackMeasurements(
      MeasurementSnapshot s
  ) {
    List<PreInfoDoc.StackMeasurementDoc> items = new ArrayList<>();
    
    for (StackMeasurement sm : safeList(s.measurements())) {
      
      if (sm.getPollutant() == null) {
        continue; // 또는 throw (정책 선택)
      }
      
      items.add(
          PreInfoDoc.StackMeasurementDoc.builder()
              .stackMeasurementId(sm.getId())
              .pollutantId(sm.getPollutant().getId())
              .pollutantNameKr(safe(sm.getPollutant().getNameKr()))
              .pollutantNameEn(safe(sm.getPollutant().getNameEn()))
              .cycle(sm.getCycle())
              .allowance(sm.getAllowance())
              .build()
      );
    }
    
    return items;
  }
  
  private EquipmentDoc buildEquipment(MeasurementSnapshot s) {
    return EquipmentDoc.builder()
        .particularEquipment(buildParticularEquipment(s))
        .pitotTube(buildPitotTube(s))
        .build();
  }
  
  private EquipmentDoc.ParticularEquipmentDoc buildParticularEquipment(MeasurementSnapshot s) {
    return EquipmentDoc.ParticularEquipmentDoc.builder()
        .particularEquipmentId(s.equipment().getId())
        .modelName(s.equipment().getModelName())
        .equipmentName(s.equipment().getEquipmentName())
        .deltaH(s.equipment().getDh())
        .Yd(s.equipment().getYd())
        .build();
  }
  
  private EquipmentDoc.PitotTubeDoc buildPitotTube(MeasurementSnapshot s) {
    return EquipmentDoc.PitotTubeDoc.builder()
        .pitotTubeId(s.pitotTube().getId())
        .modelName(s.pitotTube().getModelName())
        .equipmentName(s.pitotTube().getEquipmentName())
        .coefficients(buildCoefficientList(s))
        .build();
  }
  
  private List<EquipmentDoc.PitotTubeDoc.CoefficientDoc> buildCoefficientList(MeasurementSnapshot s) {
    List<EquipmentDoc.PitotTubeDoc.CoefficientDoc> list = new ArrayList<>();
    for (PitotTubeCoefficient coefficient : safeList(s.pitotTube().getPitotTubeCoefficientList())) {
      list.add(
          EquipmentDoc.PitotTubeDoc.CoefficientDoc.builder()
              .coefficientId(coefficient.getId())
              .velocity(coefficient.getVelocity())
              .coefficient(coefficient.getCoefficient())
              .build()
      );
    }
    
    return list;
  }
  
  private ClientDoc buildClient(MeasurementSnapshot s) {
    return ClientDoc.builder()
        .company(buildCompany(s))
        .stack(buildStack(s))
        .preventions(buildPrevention(s))
        .build();
  }
  
  private ClientDoc.CompanyDoc buildCompany(MeasurementSnapshot s) {
    return ClientDoc.CompanyDoc.builder()
        .companyId(s.company().getId())
        .companyName(safe(s.company().getName()))
        .workplaceId(s.workplace().getId())
        .workplaceName(safe(s.workplace().getName()))
        .bizNumber(safe(s.workplace().getBizNumber()))
        .ceoName(safe(s.company().getCeoName()))
        .address(safe(s.workplace().getAddress()))
        .businessCategory(safe(s.workplace().getBusinessCategory()))
        .grade(s.workplace().getGrade())
        .build();
  }
  
  private ClientDoc.StackDoc buildStack(MeasurementSnapshot s) {
    return ClientDoc.StackDoc.builder()
        .stackId(s.stack().getId())
        .name(s.stack().getName())
        .semsNumber(s.stack().getSemsNumber())
        .grade(s.stack().getGrade())
        .height(s.stack().getHeight())
        .horizontalLength(s.stack().getHorizontalLength())
        .verticalLength(s.stack().getVerticalLength())
        .shape(s.stack().getShape())
        .orientation(s.stack().getOrientation())
        .standardOxygen(s.stack().getStandardOxygen())
        .build();
  }
  
  private List<ClientDoc.PreventionDoc> buildPrevention(MeasurementSnapshot s) {
    List<ClientDoc.PreventionDoc> docs = new ArrayList<>();
    
    for (Prevention p : safeList(s.preventions())) {
      docs.add(
          ClientDoc.PreventionDoc.builder()
              .preventionId(p.getId())
              .name(safe(p.getName()))
              .facilities(buildFacilities(p))
              .targets(buildTargets(p))
              .build()
      );
    }
    
    return docs;
  }
  
  private List<ClientDoc.FacilityDoc> buildFacilities(Prevention p) {
    List<ClientDoc.FacilityDoc> facilities = new ArrayList<>();
    
    for (Facility f : safeList(p.getFacilities())) {
      facilities.add(
          ClientDoc.FacilityDoc.builder()
              .facilityId(f.getId())
              .name(safe(f.getName()))
              .fuelUsage(safe(f.getFuelUsage()))
              .itemOutput(safe(f.getItemOutput()))
              .fuelInput(safe(f.getFuelInput()))
              .fuelType(safe(f.getFuelType()))
              .build()
      );
    }
    
    return facilities;
  }
  
  private List<ClientDoc.TargetDoc> buildTargets(Prevention p) {
    List<ClientDoc.TargetDoc> targets = new ArrayList<>();
    
    for (Target t : safeList(p.getTargets())) {
      targets.add(
          ClientDoc.TargetDoc.builder()
              .targetId(t.getId())
              .targetSubstance(safe(t.getTargetSubstance()))
              .removalEfficiency(t.getRemovalEfficiency())
              .build()
      );
    }
    
    return targets;
  }
  
  private String safe(String value) {
    return value == null ? "" : value;
  }
  
  private <T> List<T> safeList(List<T> list) {
    return list == null ? List.of() : list;
  }
}