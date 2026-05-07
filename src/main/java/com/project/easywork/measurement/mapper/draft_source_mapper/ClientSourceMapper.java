package com.project.easywork.measurement.mapper.draft_source_mapper;

import com.project.easywork.client.domain.persistance.*;
import com.project.easywork.measurement.domain.dto.draft_source.client.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ClientSourceMapper {
  
  public ClientSource toSource(Stack stack) {
    
    if (stack == null) return null;
    
    Workplace workplace = stack.getWorkplace();
    Company company = workplace != null ? workplace.getCompany() : null;
    
    return new ClientSource(
        toCompanySource(company),
        toWorkplaceSource(workplace),
        toStackSource(stack)
    );
  }
  
  private CompanySource toCompanySource(Company company) {
    if (company == null) return null;
    
    return new CompanySource(
        company.getId(),
        company.getName(),
        company.getCeoName()
    );
  }
  
  private WorkplaceSource toWorkplaceSource(Workplace workplace) {
    if (workplace == null) return null;
    
    return new WorkplaceSource(
        workplace.getId(),
        workplace.getName(),
        workplace.getAddress(),
        workplace.getBizNumber(),
        workplace.getManager(),
        workplace.getBusinessCategory(),
        workplace.getGrade()
    );
  }
  
  private StackSource toStackSource(Stack stack) {
    return new StackSource(
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
        toPreventionSources(stack)
    );
  }
  
  private List<PreventionSource> toPreventionSources(Stack stack) {
    if (stack.getPreventions() == null) return List.of();
    
    return stack.getPreventions().stream()
        .filter(Objects::nonNull)
        .map(this::toPreventionSource)
        .toList();
  }
  
  private PreventionSource toPreventionSource(Prevention prevention) {
    return new PreventionSource(
        prevention.getId(),
        prevention.getName(),
        // facility / target mapping 필요
        toFacilitySources(prevention),
        toTargetSources(prevention)
    );
  }
  
  private List<PreventionSource.FacilitySource> toFacilitySources(Prevention prevention) {
    if (prevention.getFacilities() == null) return List.of();
    
    return prevention.getFacilities().stream()
        .filter(Objects::nonNull)
        .map(this::toFacilitySource)
        .toList();
  }
  
  private PreventionSource.FacilitySource toFacilitySource(Facility facility) {
    
    return new PreventionSource.FacilitySource(
        facility.getId(),
        facility.getName(),
        facility.getFuelUsage(),
        facility.getItemOutput(),
        facility.getFuelInput(),
        facility.getFuelType()
    );
  }
  
  private List<PreventionSource.TargetSource> toTargetSources(Prevention prevention) {
    if (prevention.getTargets() == null) return List.of();
    
    return prevention.getTargets().stream()
        .filter(Objects::nonNull)
        .map(this::toTargetSource)
        .toList();
  }
  
  private PreventionSource.TargetSource toTargetSource(Target target) {
    
    return new PreventionSource.TargetSource(
        target.getId(),
        target.getTargetSubstance(),
        target.getRemovalEfficiency()
    );
  }
}
