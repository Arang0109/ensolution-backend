package com.project.easywork.measurement.domain.dto.draft_source.client;

public record ClientSource(
  CompanySource company,
  WorkplaceSource workplace,
  StackSource stack
) {}