package com.project.easywork.measurement.dto.snapshot.equipment;

public record MeasurementEquipmentSnapshot(
    ParticleSamplerSnapshot particleSampler,
    GasSamplerSnapshot gasSampler,
    PitotTubeSnapshot pitotTube,
    NozzleSnapshot nozzle
) {}