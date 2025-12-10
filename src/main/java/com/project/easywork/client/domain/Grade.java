package com.project.easywork.client.domain;

import lombok.Getter;

@Getter
public enum Grade {
  TYPE_1(1),
  TYPE_2(2),
  TYPE_3(3),
  TYPE_4(4),
  TYPE_5(5);
  
  private final int number;
  
  Grade(int number) {
    this.number = number;
  }
  
  public String getNumberString() {
    return String.valueOf(number);
  }
}