package com.project.easywork.measurement.service.impl;

import com.project.easywork.client.domain.persistance.Company;
import com.project.easywork.client.domain.persistance.Prevention;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.client.domain.persistance.Workplace;
import com.project.easywork.common.resolver.DomainEntityResolver;
import com.project.easywork.measurement.dto.MeasurementStatus;
import com.project.easywork.measurement.dto.document.MeasurementDocument;
import com.project.easywork.measurement.dto.document.input.PreInfoDocument;
import com.project.easywork.plan.domain.dto.PlanCreateBundleD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MeasurementDocumentFactory {
  
  private final DomainEntityResolver resolver;
  
  public MeasurementDocument createDraft(Long planId, PlanCreateBundleD dto) {
    
    Company company = resolver.getCompanyOrThrow(dto.getCompanyId());
    Workplace workplace = resolver.getWorkplaceOrThrow(dto.getWorkplaceId());
    Stack stack = resolver.getStackOrThrow(dto.getPlan().getStackId());
    List<Prevention> preventions = stack.getPreventions();
    
    return MeasurementDocument.builder()
        .planId(planId)
        .status(MeasurementStatus.DRAFT)
        .vehicleNumber(dto.getVehicleNumber())
        .preInfo(buildPreInfo(company, workplace, stack, preventions))
        .build();
  }
  
  private PreInfoDocument buildPreInfo(
      Company company,
      Workplace workplace,
      Stack stack,
      List<Prevention> preventions
  ) {
    return PreInfoDocument.builder()
        .company(toCompanySnapshot(company))
        .workplace(toWorkplaceSnapshot(workplace))
        .stack(toStackSnapshot(stack))
        .preventions(toPreventionSnapshots(preventions))
        .build();
  }
  
  private PreInfoDocument.CompanyDocument toCompanySnapshot(Company company) {
    return PreInfoDocument.CompanyDocument.builder()
        .id(company.getId())
        .name(company.getName())
        .ceoName(company.getCeoName())
        .bizNumber(company.getBizNumber())
        .build();
  }
  
  private PreInfoDocument.WorkplaceDocument toWorkplaceSnapshot(Workplace workplace) {
    return PreInfoDocument.WorkplaceDocument.builder()
        .id(workplace.getId())
        .name(workplace.getName())
        .address(workplace.getAddress())
        .bizNumber(workplace.getBizNumber())
        .businessCategory(workplace.getBusinessCategory())
        .grade(workplace.getGrade())
        .build();
  }
  
  private PreInfoDocument.StackDocument toStackSnapshot(Stack stack) {
    return PreInfoDocument.StackDocument.builder()
        .id(stack.getId())
        .name(stack.getName())
        .semsNumber(stack.getSemsNumber())
        .grade(stack.getGrade())
        .height(stack.getHeight())
        .horizontalLength(stack.getHorizontalLength())
        .verticalLength(stack.getVerticalLength())
        .shape(stack.getShape())
        .orientation(stack.getOrientation())
        .standardOxygen(stack.getStandardOxygen())
        .build();
  }
  
  private List<PreInfoDocument.PreventionDocument> toPreventionSnapshots(List<Prevention> preventions) {
    return preventions.stream()
        .map(p -> PreInfoDocument.PreventionDocument.builder()
            .name(p.getName())
            .facilities(
                p.getFacilities().stream()
                    .map(f -> PreInfoDocument.FacilityDocument.builder()
                        .name(f.getName())
                        .fuelUsage(f.getFuelUsage())
                        .itemOutput(f.getItemOutput())
                        .fuelInput(f.getFuelInput())
                        .fuelType(f.getFuelType())
                        .build())
                    .toList()
            )
            .targets(
                p.getTargets().stream()
                    .map(t -> PreInfoDocument.TargetDocument.builder()
                        .targetSubstance(t.getTargetSubstance())
                        .removalEfficiency(t.getRemovalEfficiency())
                        .build())
                    .toList()
            )
            .build())
        .toList();
  }
}
