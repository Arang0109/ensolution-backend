package com.project.easywork.client.domain.dto.prevention;

import com.project.easywork.client.domain.dto.facility.FacilityDto;
import com.project.easywork.client.domain.dto.target.TargetDto;
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
