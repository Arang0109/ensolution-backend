package com.project.easywork.measurement.dto.snapshot.equipment;

import java.math.BigDecimal;

public record GasSamplerSnapshot(
    String equipmentId,
    String managementNumber,
    String alias,
    
    BigDecimal totalVolume
) {}