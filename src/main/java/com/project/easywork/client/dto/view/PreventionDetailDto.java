package com.project.easywork.client.dto.view;

import com.project.easywork.client.dto.FacilityDto;
import com.project.easywork.client.dto.PreventionDto;
import com.project.easywork.client.dto.TargetDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PreventionDetailDto extends PreventionDto {
  List<FacilityDto> facilities = new ArrayList<>();
  List<TargetDto> targets = new ArrayList<>();
}
