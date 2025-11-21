package com.project.easywork.common.util.measurePoint;

public class RectangleStrategy implements MeasurePointStrategy {
  @Override
  public Integer calculate(double... values) {
    double x = values[0];
    double y = values[1];
    
    double area = x * y;
    double length = determineLength(area);
    
    if (area <= 0.25) return 1;
    if (area > 20.0) return 20;
    
    int n = calculateAxisCount(x, length);
    int m = calculateAxisCount(y, length);
    
    return n * m;
  }
  
  private double determineLength(double area) {
    if (area <= 1.0) return 0.5;
    if (area <= 4.0) return 0.667;
    return 1.0;   // area <= 20.0
  }
  
  private int calculateAxisCount(double size, double maxSpacing) {
    int count = 1;
    
    while ((size / count) > maxSpacing) {
      count++;
    }
    
    return count;
  }
}
