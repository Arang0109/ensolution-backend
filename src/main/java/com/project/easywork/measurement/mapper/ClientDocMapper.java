package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.document.input.ClientDoc;
import com.project.easywork.measurement.dto.snapshot.client.*;
import com.project.easywork.report.domain.client.ClientDataD;
import com.project.easywork.report.domain.stack.FacilityDataD;
import com.project.easywork.report.domain.stack.PreventionDataD;
import com.project.easywork.report.domain.stack.StackDataD;
import com.project.easywork.report.domain.stack.TargetDataD;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ClientDocMapper {
  
  @Mapping(target = "company", expression = "java(toCompanyDoc(snapshot.company(), snapshot.workplace()))")
  @Mapping(target = "stack", source = "stack")
  ClientDoc toDoc(ClientSnapshot snapshot);
  
  @Mapping(target = "preventions", source = "preventions")
  ClientDoc.StackDoc toDoc(StackSnapshot snapshot);
  
  @Mapping(target = "facilities", source = "facilities")
  @Mapping(target = "targets", source = "targets")
  ClientDoc.PreventionDoc toDoc(PreventionSnapshot snapshot);
  ClientDoc.FacilityDoc toDoc(PreventionSnapshot.FacilitySnapshot snapshot);
  ClientDoc.TargetDoc toDoc(PreventionSnapshot.TargetSnapshot snapshot);
  
  ClientDataD toClientDataDto(ClientDoc.CompanyDoc companyDoc);
  StackDataD toStackDataDto(ClientDoc.StackDoc stackDoc);
  PreventionDataD toPreventionDataDto(ClientDoc.PreventionDoc preventionDoc);
  FacilityDataD toFacilityDataDto(ClientDoc.FacilityDoc facilityDoc);
  TargetDataD toTargetDataDto(ClientDoc.TargetDoc targetDoc);
  
  default ClientDoc.CompanyDoc toCompanyDoc(
      CompanySnapshot company,
      WorkplaceSnapshot workplace
  ) {
    
    return ClientDoc.CompanyDoc.builder()
        .companyId(company != null ? company.companyId() : null)
        .companyName(company != null ? company.name() : null)
        .ceoName(company != null ? company.ceoName() : null)
        
        .workplaceId(workplace != null ? workplace.workplaceId() : null)
        .workplaceName(workplace != null ? workplace.name() : null)
        .address(workplace != null ? workplace.address() : null)
        .bizNumber(workplace != null ? workplace.bizNumber() : null)
        .manager(workplace != null ? workplace.manager() : null)
        .businessCategory(workplace != null ? workplace.businessCategory() : null)
        .grade(workplace != null ? workplace.grade() : null)
        
        .build();
  }
}