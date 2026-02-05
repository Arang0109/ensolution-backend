package com.project.easywork.measurement.dto.snapshot.equipment;

import java.math.BigDecimal;

public record ParticleSamplerSnapshot(
    String equipmentId,
    String managementNumber,
    String alias,
    
    BigDecimal totalVolume,
    BigDecimal deltaH,
    BigDecimal yd
) {}