package com.project.easywork.measurement.dto.snapshot.client;

import com.project.easywork.client.domain.Grade;
import com.project.easywork.client.domain.Orientation;
import com.project.easywork.client.domain.Shape;

import java.math.BigDecimal;
import java.util.List;

public record StackSnapshot(
    Long stackId,
    String name,
    String semsNumber,
    Grade grade,
    BigDecimal height,
    BigDecimal horizontalLength,
    BigDecimal verticalLength,
    Shape shape,
    Orientation orientation,
    BigDecimal standardOxygen,
    List<PreventionSnapshot> preventions
) {
}