package com.project.easywork.common.exception;

import com.project.easywork.common.web.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice // @RestController 에서 발생한 모든 예외를 가로채서 처리함.
public class GlobalExceptionHandler {
  
  // 로그인 실패 예외처리
  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ApiResponse<?>> handleBadCredentials(BadCredentialsException e) {
    log.warn("[BadCredentialsException] {}", e.getMessage());
    return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(ApiResponse.error("아이디 또는 비밀번호가 일치하지 않습니다."));
  }
  
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ApiResponse<Void>> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex
  ) {
    String message = "요청 본문의 JSON 형식이 올바르지 않습니다.";
    
    // Jackson 파싱 에러 상세 분기 (선택)
    Throwable cause = ex.getCause();
    if (cause instanceof com.fasterxml.jackson.core.JsonParseException jp) {
      message = "JSON 문법 오류: " + jp.getOriginalMessage();
    }
    
    return ResponseEntity
        .badRequest()
        .body(ApiResponse.error(message));
  }
  
  // UNIQUE/FK 충돌 예외처리
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ApiResponse<Void>> handleDataIntegrityViolations(DataIntegrityViolationException e) {
    log.warn("[DataIntegrityViolationException] {}", e.getMessage());
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(ApiResponse.error("중복된 데이터입니다."));
  }
  
  // @RequestParam / @PathVariable 검증 예외처리
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ApiResponse<Void>> handleConstraintViolations(ConstraintViolationException e) {
    log.warn("[] {}", e.getMessage());
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ApiResponse.error("잘못된 요청입니다."));
  }
  
  // @Valid 검증 예외처리
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<List<FieldErrorResponse>>> handleValidation(MethodArgumentNotValidException e) {
    var errors = e.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(err -> new FieldErrorResponse(
            err.getField(),
            err.getDefaultMessage()
        ))
        .toList();
    
    log.warn("[ValidationError] {}", errors);
    
    return ResponseEntity.badRequest()
        .body(ApiResponse.error("입력값이 올바르지 않습니다.", errors));
  }
  
  // 커스텀 예외 처리
  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException e) {
    log.error("[CustomException] code: {}, userMessage: {}, cause={}",
        e.getErrorCode(),
        e.getMessage(),
        e.getCause() != null ? e.getCause().getMessage() : "N/A");
    ErrorCode errorCode = e.getErrorCode();
    return ResponseEntity
        .status(errorCode.getStatus())
        .body(ApiResponse.error(e.getMessage()));
  }
  
  // 그 외 모든 예외 처리
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<?>> handleException(Exception e) {
    log.error("[Exception] Unexpected error: ", e);
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ApiResponse.error("예외가 발생했습니다."));
  }
}