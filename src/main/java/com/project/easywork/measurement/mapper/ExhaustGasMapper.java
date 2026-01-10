package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.command.ExhaustGasCommandDto;
import com.project.easywork.measurement.dto.document.input.ExhaustGasDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExhaustGasMapper {
  ExhaustGasDocument toDocument(ExhaustGasCommandDto dto);
}