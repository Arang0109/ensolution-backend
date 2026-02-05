package com.project.easywork.measurement.dto.snapshot.client;

public record ClientSnapshot(
  CompanySnapshot company,
  WorkplaceSnapshot workplace,
  StackSnapshot stack
) {}