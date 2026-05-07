package com.project.easywork.measurement.domain.dto.draft_source.equipments;

import java.math.BigDecimal;

public record ParticleSamplerSource(
    String equipmentId,
    String managementNumber,
    String alias,
    
    BigDecimal totalVolume,
    BigDecimal deltaH,
    BigDecimal yd
) {}