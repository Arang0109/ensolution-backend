package com.project.easywork.client.domain.dto.stack;

import com.project.easywork.client.domain.dto.prevention.PreventionResponseDto;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementResponseDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StackDetailResponseDto {
  private StackResponseDto stack;
  private List<PreventionResponseDto> preventions = new ArrayList<>();
  private List<StackMeasurementResponseDto> stackMeasurements = new ArrayList<>();
}