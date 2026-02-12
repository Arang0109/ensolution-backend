package com.project.easywork.measurement.dto.snapshot.client;

import com.project.easywork.client.domain.Grade;

public record WorkplaceSnapshot(
    Long workplaceId,
    String name,
    String address,
    String bizNumber,
    String manager,
    String businessCategory,
    Grade grade
) {
}