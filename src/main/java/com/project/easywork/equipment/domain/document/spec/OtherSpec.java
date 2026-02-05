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
public class OtherSpec extends EquipmentSpec {
  
  @Override
  public EquipType getType() {
    return EquipType.OTHER;
  }
}