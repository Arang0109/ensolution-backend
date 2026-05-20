package com.project.easywork.agency.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record TeamUpdateD (
    @NotBlank(message = "필수 입력") String name,
    String vehicleNumber,
    String mentor,
    String mentee,
    String particleSamplerId,
    String gasSamplerId,
    String pitotTubeId,
    String nozzleId
) {}