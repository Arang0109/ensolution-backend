package com.project.easywork.common.exception;

import com.project.easywork.common.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  
  // 아이디/비밀번호 불일치
  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ApiResponse<?>> handleBadCredentials(BadCredentialsException e) {
    return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(new ApiResponse<>(
            false,
            "아이디 또는 비밀번호가 일치하지 않습니다.",
            null
        ));
  }
  
  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException ex) {
    ErrorCode errorCode = ex.getErrorCode();
    ApiResponse<Void> response =
        new ApiResponse<>(false, ex.getMessage(), null);
    return ResponseEntity.status(errorCode.getStatus()).body(response);
  }
  
  // 모든 예외
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<?>> handleException(Exception e) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ApiResponse<>(
            false,
            "예외가 발생했습니다.",
            null
        ));
  }
}