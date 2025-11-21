package com.project.easywork.client.dto.view;

import com.project.easywork.client.dto.StackDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StackDetailDto extends StackDto {
  List<PreventionDetailDto> preventions = new ArrayList<>();
}
