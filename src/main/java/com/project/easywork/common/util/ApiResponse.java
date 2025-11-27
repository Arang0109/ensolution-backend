package com.project.easywork.common.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
  private boolean status;
  private String message;
  private T data;
  
  public static <T> ApiResponse<T> ok(boolean status, String message) {
    return new ApiResponse<>(status, message, null);
  }
  
  public static <T> ApiResponse<T> ok(boolean status, String message, T data) {
    return new ApiResponse<>(status, message, data);
  }
  
  public static <T> ApiResponse<T> ok() {
    return new ApiResponse<>(true, "OK", null);
  }
  
  public static <T> ApiResponse<T> ok(T data) {
    return new ApiResponse<>(true, "OK", data);
  }
  
  public static <T> ApiResponse<T> error(boolean status, String message) {
    return new ApiResponse<>(status, message, null);
  }
  
}