package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.command.ClientCommandDto;
import com.project.easywork.measurement.dto.document.input.ClientDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
  ClientDocument toDocument(ClientCommandDto dto);
  
  ClientDocument.CompanyDocument toDocument(ClientCommandDto.PreInfoCompany company);
  ClientDocument.StackDocument toDocument(ClientCommandDto.PreInfoStack stack);
  ClientDocument.PreventionDocument toDocument(ClientCommandDto.PreInfoPrevention prevention);
  ClientDocument.FacilityDocument toDocument(ClientCommandDto.PreInfoFacility facility);
  ClientDocument.TargetDocument toDocument(ClientCommandDto.PreInfoTarget target);
}

