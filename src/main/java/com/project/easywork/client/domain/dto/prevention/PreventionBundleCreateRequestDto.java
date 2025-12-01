package com.project.easywork.client.domain.dto.prevention;

import com.project.easywork.client.domain.dto.facility.FacilityCreateRequestDto;
import com.project.easywork.client.domain.dto.target.TargetCreateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class PreventionBundleCreateRequestDto {
  private PreventionCreateRequestDto prevention;
  private List<FacilityCreateRequestDto> facilities;
  private List<TargetCreateRequestDto> targets;
}
