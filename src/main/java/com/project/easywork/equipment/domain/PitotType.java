package com.project.easywork.equipment.domain;

import lombok.Getter;

@Getter
public enum PitotType {
  DUST("먼지, 중금속"),
  MERCURY("수은"),
  FINE_DUST("미세먼지");
  
  private final String label;
  
  PitotType(String label) { this.label = label; }
}