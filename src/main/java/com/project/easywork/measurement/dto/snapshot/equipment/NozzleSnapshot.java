package com.project.easywork.measurement.dto.snapshot.equipment;

import java.math.BigDecimal;
import java.util.List;

public record NozzleSnapshot(
    String equipmentId,
    String managementNumber,
    String alias,
    
    List<NozzleDiameterSnapshot> diameters
) {
  public record NozzleDiameterSnapshot(
      BigDecimal diameter
  ) {}
}