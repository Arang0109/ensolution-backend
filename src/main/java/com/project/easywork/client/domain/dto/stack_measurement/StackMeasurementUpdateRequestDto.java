package com.project.easywork.client.domain.dto.stack_measurement;

import com.project.easywork.common.constant.Cycle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class StackMeasurementUpdateRequestDto {
  private Cycle cycle;
  private Double allowance;
}
