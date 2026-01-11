package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.command.ExhaustGasCommandD;
import com.project.easywork.measurement.dto.document.input.ExhaustGasDoc;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExhaustGasMapper {
  ExhaustGasDoc toDocument(ExhaustGasCommandD dto);
}