package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.domain.document.client.ClientSnapshotDoc;
import com.project.easywork.measurement.domain.dto.draft_source.client.*;
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
  ClientSnapshotDoc toDoc(ClientSource snapshot);
  
  @Mapping(target = "preventions", source = "preventions")
  ClientSnapshotDoc.StackSnapshotDoc toDoc(StackSource snapshot);
  
  @Mapping(target = "facilities", source = "facilities")
  @Mapping(target = "targets", source = "targets")
  ClientSnapshotDoc.PreventionSnapshotDoc toDoc(PreventionSource snapshot);
  ClientSnapshotDoc.FacilitySnapshotDoc toDoc(PreventionSource.FacilitySource snapshot);
  ClientSnapshotDoc.TargetSnapshotDoc toDoc(PreventionSource.TargetSource snapshot);
  
  ClientDataD toClientDataDto(ClientSnapshotDoc.CompanySnapshotDoc companyDoc);
  StackDataD toStackDataDto(ClientSnapshotDoc.StackSnapshotDoc stackDoc);
  PreventionDataD toPreventionDataDto(ClientSnapshotDoc.PreventionSnapshotDoc preventionDoc);
  FacilityDataD toFacilityDataDto(ClientSnapshotDoc.FacilitySnapshotDoc facilityDoc);
  TargetDataD toTargetDataDto(ClientSnapshotDoc.TargetSnapshotDoc targetDoc);
  
  default ClientSnapshotDoc.CompanySnapshotDoc toCompanyDoc(
      CompanySource company,
      WorkplaceSource workplace
  ) {
    
    return ClientSnapshotDoc.CompanySnapshotDoc.builder()
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