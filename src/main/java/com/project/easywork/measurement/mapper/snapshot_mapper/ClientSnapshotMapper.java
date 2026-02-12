package com.project.easywork.measurement.mapper.snapshot_mapper;

import com.project.easywork.client.domain.persistance.*;
import com.project.easywork.measurement.dto.snapshot.client.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ClientSnapshotMapper {
  
  public ClientSnapshot toSnapshot(Stack stack) {
    
    if (stack == null) return null;
    
    Workplace workplace = stack.getWorkplace();
    Company company = workplace != null ? workplace.getCompany() : null;
    
    return new ClientSnapshot(
        toCompanySnapshot(company),
        toWorkplaceSnapshot(workplace),
        toStackSnapshot(stack)
    );
  }
  
  private CompanySnapshot toCompanySnapshot(Company company) {
    if (company == null) return null;
    
    return new CompanySnapshot(
        company.getId(),
        company.getName(),
        company.getCeoName()
    );
  }
  
  private WorkplaceSnapshot toWorkplaceSnapshot(Workplace workplace) {
    if (workplace == null) return null;
    
    return new WorkplaceSnapshot(
        workplace.getId(),
        workplace.getName(),
        workplace.getAddress(),
        workplace.getBizNumber(),
        workplace.getManager(),
        workplace.getBusinessCategory(),
        workplace.getGrade()
    );
  }
  
  private StackSnapshot toStackSnapshot(Stack stack) {
    return new StackSnapshot(
        stack.getId(),
        stack.getName(),
        stack.getSemsNumber(),
        stack.getGrade(),
        stack.getHeight(),
        stack.getHorizontalLength(),
        stack.getVerticalLength(),
        stack.getShape(),
        stack.getOrientation(),
        stack.getStandardOxygen(),
        toPreventionSnapshots(stack)
    );
  }
  
  private List<PreventionSnapshot> toPreventionSnapshots(Stack stack) {
    if (stack.getPreventions() == null) return List.of();
    
    return stack.getPreventions().stream()
        .filter(Objects::nonNull)
        .map(this::toPreventionSnapshot)
        .toList();
  }
  
  private PreventionSnapshot toPreventionSnapshot(Prevention prevention) {
    return new PreventionSnapshot(
        prevention.getId(),
        prevention.getName(),
        // facility / target mapping 필요
        toFacilitySnapshots(prevention),
        toTargetSnapshots(prevention)
    );
  }
  
  private List<PreventionSnapshot.FacilitySnapshot> toFacilitySnapshots(Prevention prevention) {
    if (prevention.getFacilities() == null) return List.of();
    
    return prevention.getFacilities().stream()
        .filter(Objects::nonNull)
        .map(this::toFacilitySnapshot)
        .toList();
  }
  
  private PreventionSnapshot.FacilitySnapshot toFacilitySnapshot(Facility facility) {
    
    return new PreventionSnapshot.FacilitySnapshot(
        facility.getId(),
        facility.getName(),
        facility.getFuelUsage(),
        facility.getItemOutput(),
        facility.getFuelInput(),
        facility.getFuelType()
    );
  }
  
  private List<PreventionSnapshot.TargetSnapshot> toTargetSnapshots(Prevention prevention) {
    if (prevention.getTargets() == null) return List.of();
    
    return prevention.getTargets().stream()
        .filter(Objects::nonNull)
        .map(this::toTargetSnapshot)
        .toList();
  }
  
  private PreventionSnapshot.TargetSnapshot toTargetSnapshot(Target target) {
    
    return new PreventionSnapshot.TargetSnapshot(
        target.getId(),
        target.getTargetSubstance(),
        target.getRemovalEfficiency()
    );
  }
}
