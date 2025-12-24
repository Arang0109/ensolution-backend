package com.project.easywork.client.domain.dto.prevention;

import com.project.easywork.client.domain.dto.facility.FacilityCreateRequestDto;
import com.project.easywork.client.domain.dto.target.TargetCreateRequestDto;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PreventionBundleCreateRequestDto {
  private PreventionCreateRequestDto prevention;
  private List<FacilityCreateRequestDto> facilities;
  private List<TargetCreateRequestDto> targets;
}
