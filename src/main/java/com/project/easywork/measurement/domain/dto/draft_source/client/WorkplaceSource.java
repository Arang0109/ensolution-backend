package com.project.easywork.measurement.domain.dto.draft_source.client;

import com.project.easywork.client.domain.Grade;

public record WorkplaceSource(
    Long workplaceId,
    String name,
    String address,
    String bizNumber,
    String manager,
    String businessCategory,
    Grade grade
) {
}