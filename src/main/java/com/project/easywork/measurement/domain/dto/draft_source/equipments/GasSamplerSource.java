package com.project.easywork.measurement.domain.dto.draft_source.equipments;

import java.math.BigDecimal;

public record GasSamplerSource(
    String equipmentId,
    String managementNumber,
    String alias,
    
    BigDecimal totalVolume
) {}