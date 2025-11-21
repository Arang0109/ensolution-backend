package com.project.easywork.common.constant;

import lombok.Getter;

@Getter
public enum Size {
  TYPE_1(1),
  TYPE_2(2),
  TYPE_3(3),
  TYPE_4(4),
  TYPE_5(5);
  
  private final int number;
  
  Size(int number) {
    this.number = number;
  }
  
  public String getNumberString() {
    return String.valueOf(number);
  }
}