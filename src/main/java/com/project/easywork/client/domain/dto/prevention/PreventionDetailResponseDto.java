package com.project.easywork.client.domain.dto.prevention;

import com.project.easywork.client.domain.dto.facility.FacilityDto;
import com.project.easywork.client.domain.dto.target.TargetDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class PreventionDetailResponseDto {
  private PreventionResponseDto prevention;
  private List<FacilityDto> facilities = new ArrayList<>();
  private List<TargetDto> targets = new ArrayList<>();
}
