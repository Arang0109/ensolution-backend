package com.project.easywork.measurement.domain.dto.command;

import com.project.easywork.plan.domain.PlanStatus;

public record StatusUpdateCommandD (
  PlanStatus status
) {}
