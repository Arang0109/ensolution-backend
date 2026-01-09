package com.project.easywork.client.domain.dto.prevention;

import com.project.easywork.client.domain.dto.facility.FacilityD;
import com.project.easywork.client.domain.dto.target.TargetD;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PreventionDetailD {
  private PreventionD prevention;
  private List<FacilityD> facilities = new ArrayList<>();
  private List<TargetD> targets = new ArrayList<>();
}
