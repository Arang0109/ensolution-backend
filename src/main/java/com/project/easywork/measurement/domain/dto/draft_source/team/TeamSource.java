package com.project.easywork.measurement.domain.dto.draft_source.team;

public record TeamSource(
    Long teamId,
    String name,
    String vehicleNumber,
    String mentor,
    String mentee
) {
}
