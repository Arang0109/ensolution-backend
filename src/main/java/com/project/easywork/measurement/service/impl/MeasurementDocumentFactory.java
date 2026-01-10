package com.project.easywork.measurement.service.impl;

import com.project.easywork.client.domain.persistance.*;
import com.project.easywork.measurement.dto.MeasurementStatus;
import com.project.easywork.measurement.dto.document.MeasurementDocument;
import com.project.easywork.measurement.dto.document.input.ClientDocument;
import com.project.easywork.measurement.dto.document.input.PreInfoDocument;
import com.project.easywork.measurement.dto.snapshot.MeasurementSnapshot;
import com.project.easywork.plan.domain.dto.PlanCreateD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MeasurementDocumentFactory {
  
  public MeasurementDocument createDraft(Long planId, PlanCreateD plan, MeasurementSnapshot s) {
  
    return MeasurementDocument.builder()
        .planId(planId)
        .status(MeasurementStatus.DRAFT)
        .preInfo(buildPreInfo(plan, s))
        .client(buildClient(plan, s))
        .build();
  }
  
  private PreInfoDocument buildPreInfo(PlanCreateD plan, MeasurementSnapshot s) {
    
    List<String> engineers = new ArrayList<>();
    if (s.senior() != null) {
      engineers.add(s.senior().getName());
    }
    if (s.junior() != null) {
      engineers.add(s.junior().getName());
    }
    
    return PreInfoDocument.builder()
        .measureDate(plan.getMeasureDate())
        .measurementType(plan.getMeasurementType())
        .teamName(safe(s.team().getName()))
        .vehicleNumber(safe(s.vehicleNumber()))
        .engineers(engineers)
        .measurementItems(buildStackMeasurements(s))
        .particularEquipmentName(
            s.equipment() != null ? safe(s.equipment().getEquipmentName()) : ""
        )
        .pitotTubeName(
            s.pitotTube() != null ? safe(s.pitotTube().getEquipmentName()) : ""
        )
        .build();
  }
  
  private List<PreInfoDocument.StackMeasurementDocument> buildStackMeasurements(
      MeasurementSnapshot s
  ) {
    List<PreInfoDocument.StackMeasurementDocument> items = new ArrayList<>();
    
    for (StackMeasurement sm : safeList(s.measurements())) {
      
      if (sm.getPollutant() == null) {
        continue; // 또는 throw (정책 선택)
      }
      
      items.add(
          PreInfoDocument.StackMeasurementDocument.builder()
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
  
  
  private ClientDocument buildClient(PlanCreateD plan, MeasurementSnapshot s) {
    return ClientDocument.builder()
        .company(buildCompany(s))
        .stack(buildStack(s))
        .preventions(buildPrevention(s))
        .build();
  }
  
  private ClientDocument.CompanyDocument buildCompany(MeasurementSnapshot s) {
    return ClientDocument.CompanyDocument.builder()
        .companyName(safe(s.company().getName()))
        .workplaceName(safe(s.workplace().getName()))
        .bizNumber(safe(s.workplace().getBizNumber()))
        .ceoName(safe(s.company().getCeoName()))
        .address(safe(s.workplace().getAddress()))
        .businessCategory(safe(s.workplace().getBusinessCategory()))
        .grade(s.workplace().getGrade())
        .build();
  }
  
  private ClientDocument.StackDocument buildStack(MeasurementSnapshot s) {
    return ClientDocument.StackDocument.builder()
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
  
  private List<ClientDocument.PreventionDocument> buildPrevention(MeasurementSnapshot s) {
    List<ClientDocument.PreventionDocument> docs = new ArrayList<>();
    
    for (Prevention p : safeList(s.preventions())) {
      docs.add(
          ClientDocument.PreventionDocument.builder()
              .name(safe(p.getName()))
              .facilities(buildFacilities(p))
              .targets(buildTargets(p))
              .build()
      );
    }
    
    return docs;
  }
  
  private List<ClientDocument.FacilityDocument> buildFacilities(Prevention p) {
    List<ClientDocument.FacilityDocument> facilities = new ArrayList<>();
    
    for (Facility f : safeList(p.getFacilities())) {
      facilities.add(
          ClientDocument.FacilityDocument.builder()
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
  
  private List<ClientDocument.TargetDocument> buildTargets(Prevention p) {
    List<ClientDocument.TargetDocument> targets = new ArrayList<>();
    
    for (Target t : safeList(p.getTargets())) {
      targets.add(
          ClientDocument.TargetDocument.builder()
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