package com.project.easywork.client.domain.dto.workplace;

import com.project.easywork.client.domain.dto.stack.StackResponseDto;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class WorkplaceDetailResponseDto {
  private WorkplaceResponseDto workplace;
  private List<StackResponseDto> stacks;
}