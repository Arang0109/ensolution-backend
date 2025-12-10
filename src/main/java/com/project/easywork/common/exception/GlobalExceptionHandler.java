package com.project.easywork.common.exception;

import com.project.easywork.common.api.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice // @RestController 에서 발생한 모든 예외를 가로채서 처리함.
public class GlobalExceptionHandler {
  
  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ApiResponse<?>> handleBadCredentials(BadCredentialsException e) {
    log.warn("[BadCredentialsException] {}", e.getMessage());
    return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(new ApiResponse<>(false, "아이디 또는 비밀번호가 일치하지 않습니다.", null));
  }
  
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException e) {
    String message = e.getBindingResult().getFieldErrors().stream()
        .map(err -> err.getField() + ": " + err.getDefaultMessage())
        .collect(Collectors.joining(", "));
    
    return ResponseEntity
        .badRequest()
        .body(ApiResponse.error(message));
  }
  
  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException ex) {
    log.error("[CustomException] code: {}, message: {}",
        ex.getErrorCode(), ex.getMessage());
    ErrorCode errorCode = ex.getErrorCode();
    return ResponseEntity
        .status(errorCode.getStatus())
        .body(new ApiResponse<>(false, ex.getMessage(), null));
  }
  
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<?>> handleException(Exception e) {
    log.error("[Exception] Unexpected error: ", e);
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ApiResponse<>(false, "예외가 발생했습니다.", null));
  }
}