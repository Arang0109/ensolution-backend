package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.command.ClientCommandD;
import com.project.easywork.measurement.dto.document.input.ClientDoc;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
  ClientDoc toDocument(ClientCommandD dto);
  
  ClientDoc.CompanyDoc toDocument(ClientCommandD.Company company);
  ClientDoc.StackDoc toDocument(ClientCommandD.Stack stack);
  ClientDoc.PreventionDoc toDocument(ClientCommandD.Prevention prevention);
  ClientDoc.FacilityDoc toDocument(ClientCommandD.Facility facility);
  ClientDoc.TargetDoc toDocument(ClientCommandD.Target target);
}

