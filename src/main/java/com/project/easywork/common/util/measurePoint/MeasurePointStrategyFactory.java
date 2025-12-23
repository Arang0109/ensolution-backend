package com.project.easywork.common.util.measurePoint;

public class MeasurePointStrategyFactory {
  public static MeasurePointStrategy of(String shape) {
    
    return switch (shape.toLowerCase()) {
      case "rectangular" -> new RectangleStrategy();
      case "circular" -> new CircleStrategy();
      default -> throw new IllegalArgumentException(shape + " : 지원하지 않는 모양입니다.");
    };
  }
}