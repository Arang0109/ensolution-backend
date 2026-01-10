package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.command.ClientCommandDto;
import com.project.easywork.measurement.dto.document.input.ClientDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
  ClientDocument toDocument(ClientCommandDto dto);
  
  ClientDocument.CompanyDocument toDocument(ClientCommandDto.PreInfoCompany company);
  ClientDocument.StackDocument toDocument(ClientCommandDto.PreInfoStack dto);
  ClientDocument.PreventionDocument toDocument(ClientCommandDto.PreInfoPrevention dto);
  ClientDocument.FacilityDocument toDocument(ClientCommandDto.PreInfoFacility dto);
  ClientDocument.TargetDocument toDocument(ClientCommandDto.PreInfoTarget dto);
}

