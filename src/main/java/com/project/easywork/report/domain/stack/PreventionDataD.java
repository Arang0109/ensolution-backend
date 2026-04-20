package com.project.easywork.report.domain.stack;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PreventionDataD {
  private String name;
  private List<FacilityDataD> facilities;
  private List<TargetDataD> targets;
}