package com.project.easywork.client.domain.dto.stack_measurement;

import com.project.easywork.client.domain.Cycle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StackMeasurementUpdateD {
  private Cycle cycle;
  private Double allowance;
}
