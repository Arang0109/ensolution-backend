package com.project.easywork.measurement.pipeline.domain;

import com.project.easywork.measurement.dto.command.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Measurement {
  private final MeasurementCommandDto measurement;
}