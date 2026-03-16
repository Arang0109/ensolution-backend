package com.project.easywork.measurement.dto;

import com.project.easywork.plan.domain.PlanStatus;

public record StatusUpdateCommandD (
  PlanStatus status
) {}
