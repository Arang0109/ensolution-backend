package com.project.easywork.measurement.mapper.draft_source_mapper;

import com.project.easywork.measurement.domain.dto.draft_source.basic_info.BasicInfoSource;
import com.project.easywork.plan.domain.MeasurementField;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BasicInfoSourceMapper {
  
  public BasicInfoSource toSource(
      String referenceNumber,
      LocalDate measureDate,
      MeasurementField measurementField,
      String measurementType
  ) {
    return new BasicInfoSource(
        referenceNumber,
        measureDate,
        measurementField,
        measurementType
    );
  }
}