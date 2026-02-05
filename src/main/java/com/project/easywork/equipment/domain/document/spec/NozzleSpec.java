package com.project.easywork.equipment.domain.document.spec;

import com.project.easywork.equipment.domain.EquipType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class NozzleSpec extends EquipmentSpec {
  private List<NozzleDiameterSpec> diameters;
  
  @Override
  public EquipType getType() {
    return EquipType.NOZZLE;
  }
  
  @Getter
  @Builder(toBuilder = true)
  @NoArgsConstructor
  @AllArgsConstructor
  public static class NozzleDiameterSpec {
    private EquipType type;
    private BigDecimal diameter;
  }
}