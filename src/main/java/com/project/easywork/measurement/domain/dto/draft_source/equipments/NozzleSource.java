package com.project.easywork.measurement.domain.dto.draft_source.equipments;

import java.math.BigDecimal;
import java.util.List;

public record NozzleSource(
    String equipmentId,
    String managementNumber,
    String alias,
    
    List<NozzleDiameterSource> diameters
) {
  public record NozzleDiameterSource(
      BigDecimal diameter
  ) {}
}