package com.project.easywork.equipment.domain;

import lombok.Getter;

@Getter
public enum EquipType {
  PARTICULAR("입자상"),
  GAS("가스상"),
  OTHER("기타");
  
  private final String label;
  
  EquipType(String label) { this.label = label; }
}