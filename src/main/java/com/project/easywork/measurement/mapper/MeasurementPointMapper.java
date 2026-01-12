package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.command.MeasurementPointCommandD;
import com.project.easywork.measurement.dto.document.input.MeasurementPointDoc;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeasurementPointMapper {
  MeasurementPointDoc toDocument(MeasurementPointCommandD dto);
}

