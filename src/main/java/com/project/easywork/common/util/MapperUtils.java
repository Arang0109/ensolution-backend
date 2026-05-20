package com.project.easywork.common.util;

public class MapperUtils {
  public static String normalize(String value) {
    if (value == null) {
      return null;
    }
    
    String trimmed = value.trim();
    
    return trimmed.isBlank() ? null : trimmed;
  }
}
