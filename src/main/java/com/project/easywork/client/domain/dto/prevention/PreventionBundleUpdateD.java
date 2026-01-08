package com.project.easywork.client.domain.dto.prevention;

import com.project.easywork.client.domain.dto.facility.FacilityUpdateD;
import com.project.easywork.client.domain.dto.target.TargetUpdateD;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PreventionBundleUpdateD {
  private PreventionUpdateD prevention;
  private List<FacilityUpdateD> facilities;
  private List<TargetUpdateD> targets;
}
