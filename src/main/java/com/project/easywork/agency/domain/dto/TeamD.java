package com.project.easywork.agency.domain.dto;

import java.time.LocalDateTime;

public record TeamD (
    Long id,
    String name,
    String vehicleNumber,
    String mentor,
    String mentee,
    String particleSamplerId,
    String gasSamplerId,
    String pitotTubeId,
    String nozzleId,
    LocalDateTime createdAt,
    LocalDateTime modifiedAt
) {}