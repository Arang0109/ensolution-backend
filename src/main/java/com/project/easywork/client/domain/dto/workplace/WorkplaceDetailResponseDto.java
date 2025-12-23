package com.project.easywork.client.domain.dto.workplace;

import com.project.easywork.client.domain.dto.stack.StackResponseDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkplaceDetailResponseDto {
  private WorkplaceResponseDto workplace;
  private List<StackResponseDto> stacks = new ArrayList<>();
}