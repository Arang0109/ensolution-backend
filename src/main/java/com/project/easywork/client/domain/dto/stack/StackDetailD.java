package com.project.easywork.client.domain.dto.stack;

import com.project.easywork.client.domain.dto.prevention.PreventionDetailD;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementD;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StackDetailD {
  private StackD stack;
  private List<PreventionDetailD> preventions = new ArrayList<>();
  private List<StackMeasurementD> stackMeasurements = new ArrayList<>();
}