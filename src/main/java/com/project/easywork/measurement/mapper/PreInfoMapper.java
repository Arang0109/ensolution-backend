package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.command.PreInfoCommandDto;
import com.project.easywork.measurement.dto.document.input.PreInfoDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PreInfoMapper {
  PreInfoDocument toDocument(PreInfoCommandDto dto);
  
  PreInfoDocument.CompanyDocument toDocument(PreInfoCommandDto.PreInfoCompany dto);
  PreInfoDocument.WorkplaceDocument toDocument(PreInfoCommandDto.PreInfoWorkplace dto);
  PreInfoDocument.StackDocument toDocument(PreInfoCommandDto.PreInfoStack dto);
  PreInfoDocument.PreventionDocument toDocument(PreInfoCommandDto.PreInfoPrevention dto);
  PreInfoDocument.FacilityDocument toDocument(PreInfoCommandDto.PreInfoFacility dto);
  PreInfoDocument.TargetDocument toDocument(PreInfoCommandDto.PreInfoTarget dto);
}

