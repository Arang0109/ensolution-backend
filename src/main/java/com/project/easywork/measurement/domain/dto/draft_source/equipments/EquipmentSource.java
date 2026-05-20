package com.project.easywork.measurement.domain.dto.draft_source.equipments;

public record EquipmentSource(
    ParticleSamplerSource particleSampler,
    GasSamplerSource gasSampler,
    PitotTubeSource pitotTube,
    NozzleSource nozzle
) {}