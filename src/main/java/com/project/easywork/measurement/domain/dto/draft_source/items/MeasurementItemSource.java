package com.project.easywork.measurement.domain.dto.draft_source.items;

import com.project.easywork.client.domain.Cycle;
import com.project.easywork.pollutant.domain.Method;

import java.math.BigDecimal;

public record MeasurementItemSource(
    Long stackMeasurementId,
    Long pollutantId,
    String pollutantNameKr,
    String pollutantNameEn,
    Method method,
    String testEquipment,
    String testMethod,
    Double samplingTime,
    String samplingVolume,
    Cycle cycle,
    BigDecimal allowance
) {}