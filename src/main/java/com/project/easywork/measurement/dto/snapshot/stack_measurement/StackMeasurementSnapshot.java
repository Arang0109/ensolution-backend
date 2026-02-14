package com.project.easywork.measurement.dto.snapshot.stack_measurement;

import com.project.easywork.client.domain.Cycle;
import com.project.easywork.pollutant.domain.Method;

import java.math.BigDecimal;

public record StackMeasurementSnapshot(
    Long stackMeasurementId,
    Long pollutantId,
    String pollutantNameKr,
    String pollutantNameEn,
    Method method,
    String equipmentName,
    String testMethodName,
    Double samplingTime,
    String samplingVolume,
    Cycle cycle,
    BigDecimal allowance
) {}