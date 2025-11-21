package com.project.easywork.common.util.measurePoint;

public class CircleStrategy implements MeasurePointStrategy {
  @Override
  public Integer calculate(double...values) {
    double diameter = values[0];
    
    double radius = diameter / 2.0;
    double area = Math.PI * radius * radius;
    
    if (area <= 0.25) return 1;
    if (diameter <= 1.0) return 4;
    if (diameter <= 2.0) return 8;
    if (diameter <= 4.0) return 12;
    if (diameter <= 4.5) return 16;
    
    return 20;
  }
}
