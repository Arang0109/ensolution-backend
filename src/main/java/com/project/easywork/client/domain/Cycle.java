package com.project.easywork.client.domain;

import lombok.Getter;

@Getter
public enum Cycle {
  MONTHLY_1("1회/월"),
  MONTHLY_2("2회/월"),
  BIMONTHLY("2회/2월"),
  QUARTERLY("1회/분기"),
  SEMI_ANNUAL("1회/반기"),
  ANNUAL("1회/연");
  
  private final String label;
  
  Cycle(String label) {
    this.label = label;
  }
}
