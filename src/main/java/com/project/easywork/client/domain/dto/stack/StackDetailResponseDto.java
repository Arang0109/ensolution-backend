package com.project.easywork.client.domain.dto.stack;

import com.project.easywork.client.domain.dto.prevention.PreventionResponseDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class StackDetailResponseDto {
  private StackResponseDto stack;
  private List<PreventionResponseDto> preventions = new ArrayList<>();
}