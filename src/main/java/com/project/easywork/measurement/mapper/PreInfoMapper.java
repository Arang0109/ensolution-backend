package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.command.PreInfoCommandDto;
import com.project.easywork.measurement.dto.document.input.PreInfoDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PreInfoMapper {
  PreInfoDocument toDocument(PreInfoCommandDto dto);
  
  PreInfoDocument.PreInfoCompanyDocument toDocument(PreInfoCommandDto.PreInfoCompany dto);
  PreInfoDocument.PreInfoWorkplaceDocument toDocument(PreInfoCommandDto.PreInfoWorkplace dto);
  PreInfoDocument.PreInfoStackDocument toDocument(PreInfoCommandDto.PreInfoStack dto);
  PreInfoDocument.PreInfoPreventionDocument toDocument(PreInfoCommandDto.PreInfoPrevention dto);
  PreInfoDocument.PreInfoFacilityDocument toDocument(PreInfoCommandDto.PreInfoFacility dto);
  PreInfoDocument.PreInfoTargetDocument toDocument(PreInfoCommandDto.PreInfoTarget dto);
}

