package com.project.easywork.common.util.measurePoint;

public class MeasurePointStrategyFactory {
  public static MeasurePointStrategy of(String shape) {
    
    return switch (shape.toLowerCase()) {
      case "rectangle" -> new RectangleStrategy();
      case "circle" -> new CircleStrategy();
      default -> throw new IllegalArgumentException(shape + " : 지원하지 않는 모양입니다.");
    };
  }
}