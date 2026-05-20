package com.project.easywork.common.web;

public record ApiResponse<T> (
    boolean status,
    String message,
    T data
) {
  // 성공 기본 응답
  public static <T> ApiResponse<T> success() {
    return new ApiResponse<>(true, "OK", null);
  }
  
  // 성공 + 데이터
  public static <T> ApiResponse<T> success(T data) {
    return new ApiResponse<>(true, "OK", data);
  }
  
  // 성공 + 메시지 + 데이터
  public static <T> ApiResponse<T> success(String message, T data) {
    return new ApiResponse<>(true, message, data);
  }
  
  // 실패 기본 메시지
  public static <T> ApiResponse<T> error(String message) {
    return new ApiResponse<>(false, message, null);
  }
  
  // 실패 + 에러 데이터 포함
  public static <T> ApiResponse<T> error(String message, T data) {
    return new ApiResponse<>(false, message, data);
  }
}