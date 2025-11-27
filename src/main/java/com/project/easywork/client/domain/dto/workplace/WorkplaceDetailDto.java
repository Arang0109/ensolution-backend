package com.project.easywork.client.domain.dto.workplace;

import com.project.easywork.client.domain.dto.stack.StackDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WorkplaceDetailDto extends WorkplaceDto {
  private List<StackDto> stacks = new ArrayList<>();
}
