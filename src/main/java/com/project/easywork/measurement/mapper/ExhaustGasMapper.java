package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.command.ExhaustGasCommandDto;
import com.project.easywork.measurement.dto.document.input.ExhaustGasDocument;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ExhaustGasMapper {
  ExhaustGasDocument toDocument(ExhaustGasCommandDto dto);
  ExhaustGasDocument.DynamicPressureDocument toDocument(ExhaustGasCommandDto.MeasurementPoint.DynamicPressure dto);
  ExhaustGasDocument.StaticPressureDocument toDocument(ExhaustGasCommandDto.MeasurementPoint.StaticPressure dto);
}