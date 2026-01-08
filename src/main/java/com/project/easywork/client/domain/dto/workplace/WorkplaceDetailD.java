package com.project.easywork.client.domain.dto.workplace;

import com.project.easywork.client.domain.dto.stack.StackD;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkplaceDetailD {
  private WorkplaceD workplace;
  private List<StackD> stacks = new ArrayList<>();
}