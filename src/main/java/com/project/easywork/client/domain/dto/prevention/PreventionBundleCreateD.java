package com.project.easywork.client.domain.dto.prevention;

import com.project.easywork.client.domain.dto.facility.FacilityCreateD;
import com.project.easywork.client.domain.dto.target.TargetCreateD;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PreventionBundleCreateD {
  private PreventionCreateD prevention;
  private List<FacilityCreateD> facilities;
  private List<TargetCreateD> targets;
}
