package com.project.easywork.client.dto.view;

import com.project.easywork.client.dto.StackDto;
import com.project.easywork.client.dto.WorkplaceDto;
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
