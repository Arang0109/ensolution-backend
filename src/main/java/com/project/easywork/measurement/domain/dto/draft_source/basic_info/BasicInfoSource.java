package com.project.easywork.measurement.domain.dto.draft_source.basic_info;

import com.project.easywork.plan.domain.MeasurementField;

import java.time.LocalDate;

public record BasicInfoSource(
    String referenceNumber,
    LocalDate measureDate,
    MeasurementField measurementField,
    String measurementType
) {}
