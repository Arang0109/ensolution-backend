package com.project.easywork.agency.domain.dto;

public record TeamUpdateCommand(
    String name,
    String vehicleNumber,
    String mentor,
    String mentee,
    String particleSamplerId,
    String gasSamplerId,
    String pitotTubeId,
    String nozzleId
) {
}